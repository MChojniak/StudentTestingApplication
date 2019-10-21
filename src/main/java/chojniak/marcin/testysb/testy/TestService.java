package chojniak.marcin.testysb.testy;

import chojniak.marcin.testysb.testy.question.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class TestService {
    private Logger logger = LoggerFactory.getLogger(TestService.class);
    private TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public Test addTest(String name){
        Test test = new Test(name);
        return testRepository.save(test);
    }
    public Test findById(Long id){
        Test test = testRepository.findById(id).get();
        test.getQuestions().size();
        return test;
    }



    public Set<Test> findAllTests() {
    return testRepository.findAll();
}
}
