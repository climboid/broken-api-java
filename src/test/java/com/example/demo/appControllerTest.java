package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.Is.isA;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(appController.class)
public class appControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private reverse _reverse;

    @MockBean
    private greetPerson _greetPerson;

    private person _person;

    @Before
    public void setUp() {
        this._person = new person();
    }

    @Test
    public void should_reverse_simple_strings() throws Exception {

        final String inputStr = "hello world!";
        final String outputStr = "!dlrow olleh";

        when(_reverse.reversed(any())).thenCallRealMethod();

        mvc.perform(get("/reverse?str=" + inputStr)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(outputStr)));
    }

    @Test
    public void should_reverse_very_long_strings() throws Exception {

        final String inputStr = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?";
        final String outputStr = "?rutairap allun satpulov ouq taiguf mue merolod iuq mulli lev ,rutauqesnoc eaitselom lihin mauq esse tilev etatpulov ae ni iuq tiredneherper erui mue lev metua siuQ ?rutauqesnoc idommoc ae xe diuqila tu isin ,masoirobal tipicsus siroproc mallu menoitaticrexe murtson siuq ,mainev aminim da mine tU .metatpulov tareauq mauqila mangam erolod te erobal tu tnudicni aropmet idom suie mauqmun non aiuq des ,tilev icsipida ,rutetcesnoc ,tema tis rolod aiuq muspi merolod iuq ,tse mauqsiuq orrop euqeN .tnuicsen iuqes metatpulov enoitar iuq soe serolod ingam rutnuuqesnoc aiuq des ,tiguf tua tido tua rutanrepsa tis satpulov aiuq metatpulov maspi mine omeN .obacilpxe tnus atcid eativ eataeb otcetihcra isauq te sitatirev erotnevni olli ba eauq aspi euqae ,mairepa mer matot ,muitnadual euqmerolod muitnasucca metatpulov tis rorre sutan etsi sinmo ednu sitaicipsrep tu deS";

        when(_reverse.reversed(any())).thenCallRealMethod();

        mvc.perform(get("/reverse?str=" + inputStr)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(outputStr)));
    }

    @Test
    public void should_reverse_strings_containing_two_byte_UTF_16_characters() throws Exception {

        final String inputStr = "Lorem ipsum \uD834\uDF06 dolor sit amȇἕt.";
        final String outputStr = ".tἕȇma tis rolod \uD834\uDF06 muspi meroL";

        when(_reverse.reversed(any())).thenCallRealMethod();

        mvc.perform(get("/reverse?str=" + inputStr)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(outputStr)));
    }

    @Test
    public void should_return_a_reversed_integer_if_one_is_provided_as_input() throws Exception {

        when(_reverse.reversed(any())).thenCallRealMethod();

        mvc.perform(get("/reverse?str=309834")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(438903)))
                .andExpect(jsonPath("$", isA(Integer.class)));
    }

    @Test
    public void should_return_a_reversed_float_if_one_is_provided_as_input() throws Exception {

        when(_reverse.reversed(any())).thenCallRealMethod();

        mvc.perform(get("/reverse?str=34.09")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(90.43)))
                .andExpect(jsonPath("$", isA(Double.class)));
    }

    @Test
    public void should_greet_the_person_and_have_person_be_the_only_required_property() throws Exception {

        _person.setName("Anne");

        when(_greetPerson.greetPerson(any())).thenCallRealMethod();

        mvc.perform(post("/person")
                .contentType(APPLICATION_JSON)
                .content(json(_person)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.greeting", is("Hi Anne, how are you?")))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void should_tell_the_person_how_many_Apples_they_have() throws Exception {

        when(_greetPerson.greetPerson(any())).thenCallRealMethod();

        mvc.perform(post("/person")
                .contentType(APPLICATION_JSON)
                .content(json(_person)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.apples", is(12)))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void should_greet_the_persons_pet() throws Exception {

        _person.setName("Anne");
        _person.setPets(new String[]{"Buster"});

        when(_greetPerson.greetPerson(any())).thenCallRealMethod();

        mvc.perform(post("/person")
                .contentType(APPLICATION_JSON)
                .content(json(_person)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.greetPets", is("Hi Buster, YOU'RE JUST SO FLUFFY! :O")))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void should_greet_all_the_persons_pets() throws Exception {

        _person.setName("Anne");
        _person.setPets(new String[]{"Buster", "Rocco"});

        when(_greetPerson.greetPerson(any())).thenCallRealMethod();

        mvc.perform(post("/person")
                .contentType(APPLICATION_JSON)
                .content(json(_person)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.greetPets", is("Hi Buster, YOU'RE JUST SO FLUFFY! :O Hi Rocco, YOU'RE JUST SO FLUFFY! :O")))
                .andDo(print())
                .andReturn();
    }

    protected String json(Object o) throws IOException {

        String str = objectMapper.writeValueAsString(o);

        return str;
    }

}