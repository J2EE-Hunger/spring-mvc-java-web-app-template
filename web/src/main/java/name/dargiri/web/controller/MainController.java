package name.dargiri.web.controller;

import name.dargiri.data.dto.PersonDTO;
import name.dargiri.data.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

/**
 * Created by dionis on 2/3/14.
 */

@Controller
@RequestMapping(value = {""})
public class MainController {
    public static class PersonForm {
        private UUID id;
        private String username;

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }


    @Autowired
    private PersonService personService;

    @RequestMapping(value = {"/", "/all"})
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("main/people");
        List<PersonDTO> all = personService.findAll();
        mav.addObject("people", all);
        return mav;
    }

    @RequestMapping(value = "/create")
    public ModelAndView createForm()

    {
        ModelAndView mav = new ModelAndView("main/person");
        mav.addObject("person", new PersonDTO(null, null));
        return mav;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(PersonForm personForm)

    {
        personService.create(toDTO(personForm));
        return new ModelAndView("redirect:/all");
    }


    private PersonDTO toDTO(PersonForm personForm)

    {
        return new PersonDTO(personForm.id, personForm.username);
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editForm(@PathVariable("id") UUID id) {
        PersonDTO personDTO = personService.find(id);
        if (personDTO != null) {
            ModelAndView mav = new ModelAndView("main/person");
            mav.addObject("person", personDTO);
            return mav;
        } else {
            return new ModelAndView("redirect:/");
        }
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView edit(PersonForm personForm) {
        PersonDTO person = toDTO(personForm);
        PersonDTO updated = personService.update(person);
        if (updated != null) {
            return new ModelAndView("redirect:/");
        } else {
            ModelAndView mav = new ModelAndView("main/person");
            mav.addObject("person", person);
            return mav;
        }
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") UUID id) {
        personService.delete(id);
        return new ModelAndView("redirect:/");
    }
}
