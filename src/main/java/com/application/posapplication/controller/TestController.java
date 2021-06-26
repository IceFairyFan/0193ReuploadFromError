package com.application.posapplication.controller;

import com.application.posapplication.model.Blog;
import com.application.posapplication.model.BlogMockedData;
import com.application.posapplication.model.Type;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.application.posapplication.model.Type.*;

@RestController
@RequestMapping(value = "/api/v1")
public class TestController {

    @Autowired
    private StanfordCoreNLP stanfordCoreNLP;

    private List<String> collectList(List<CoreLabel> coreLabels, final Type type){
        return coreLabels
                .stream()
                .filter(coreLabel -> type.getName().equalsIgnoreCase(coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class)))
                .map(CoreLabel::originalText)
                .collect(Collectors.toList());
    }

//    private List<String> collectList(List<CoreLabel> coreLabels, final Modal modal){
//        return coreLabels
//                .stream()
//                .filter(coreLabel -> modal.getName().equalsIgnoreCase(coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class)))
//                .map(CoreLabel::originalText)
//                .collect(Collectors.toList());
//    }

    BlogMockedData blogMockedData = BlogMockedData.getInstance();

    @PostMapping
    @RequestMapping(value="/ssplit")
//    public List<CoreSentence> ssplit(String input){
    public ResponseEntity<String> ssplit(@RequestBody String input, Type type) {
        //String inputTest = "This is the first sentence. This is the second sentence. This is the third sentence.";
        CoreDocument coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreSentence> coreSentences = coreDocument.sentences();


        for (CoreSentence sentence : coreSentences) {
            CoreDocument coreDocument1 = new CoreDocument(String.valueOf(sentence));
            stanfordCoreNLP.annotate(coreDocument1);
        }

        //Autocompleted by java
        //return coreSentences;
        //Baca dokumentasi ResponseEntity
        return ResponseEntity.ok().body(coreSentences.toString());
        //return new HashSet<>(collectList(coreSentences, type));
    }

    @PostMapping
    @RequestMapping(value="/pos")
    public Set<String> pos(@RequestBody final String input, @RequestParam final Type type){

        CoreDocument coreDocument = new CoreDocument(input);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabels = coreDocument.tokens();

        return new HashSet<>();
    }

    @PostMapping
    @RequestMapping(value="/posnoun")
    public Set<String> posnoun(@RequestBody final String input) {
//    //public Set<String> pos(@RequestBody final String input, Type type){
        Type[] listoftype = new Type[]{
                NNPS,
                NNS,
                NNP,
                NN
        };

        HashSet<String> tmp = new HashSet();
        for (int i = 0; i < listoftype.length; i++) {
            CoreDocument coreDocument = new CoreDocument(input);
            stanfordCoreNLP.annotate(coreDocument);
            List<CoreLabel> coreLabels = coreDocument.tokens();
            HashSet<String> doub = new HashSet<>(collectList(coreLabels, listoftype[i]));
            tmp.addAll(doub);
        }
        return tmp;
    }

    @PostMapping
    @RequestMapping(value="/posverb")
    public Set<String> posverb(@RequestBody final String input){

        Type[] listoftype = new Type[]{
          VB,
          VBD,
          VBG,
          VBN,
          VBP,
          VBZ
        };

        HashSet<String> tmp = new HashSet();
        for(int i = 0; i < listoftype.length; i++){
            CoreDocument coreDocument = new CoreDocument(input);
            stanfordCoreNLP.annotate(coreDocument);
            List<CoreLabel> coreLabels = coreDocument.tokens();
            HashSet<String> doub = new HashSet<>(collectList(coreLabels, listoftype[i]));
            tmp.addAll(doub);
        }
        return tmp;
        //return null;
    }

    @GetMapping("/blog")
    public List<Blog> index(){
//        return blogMockedData.fetchBlogs();
        return blogMockedData.searchBlogs("a");
    }

    @GetMapping("/blog/{id}")
    public Blog show(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        return blogMockedData.getBlogById(blogId);
    }

    @PostMapping("/blog/search")
    public List<Blog> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return blogMockedData.searchBlogs(searchTerm);
    }

    @PostMapping("/blog")
    public Blog create(@RequestBody Map<String, String> body){
        int id = Integer.parseInt(body.get("id"));
        String title = body.get("title");
        String content = body.get("content");
        return blogMockedData.createBlog(id, title, content);
    }

    @PutMapping("/blog/{id}")
    public Blog update(@PathVariable String id, @RequestBody Map<String, String> body){
        int blogId = Integer.parseInt(id);
        String title = body.get("title");
        String content = body.get("content");
        return blogMockedData.updateBlog(blogId, title, content);
    }

    @DeleteMapping("blog/{id}")
    public boolean delete(@PathVariable String id){
        int blogId = Integer.parseInt(id);
        return blogMockedData.delete(blogId);
    }

    @GetMapping("blog/template")
    public String toString(){
        BlogMockedData blogMockedData = new BlogMockedData();
        blogMockedData.toString();
        return "text";
    }
}
