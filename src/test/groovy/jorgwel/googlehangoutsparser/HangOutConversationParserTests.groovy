package jorgwel.googlehangoutsparser

import spock.lang.Specification

import java.lang.reflect.Constructor

/**
 * Created by jorgwel on 27/03/16.
 */
class HangOutConversationParserTests extends Specification{
    
//    void "Exists a class HangOutConversationParser"(){
//        when:
//            Object instance = HangOutConversationParser.class.newInstance()
//        then:
//            assert instance != null
//    }

    void "There's no a default constructor"(){
        when:
            def instance = new HangOutConversationParser()
//            def constructors = HangOutConversationParser.getDeclaredConstructors()
//            def defaultConstructor
//            constructors.each { constructor ->
//                if(doesConstructorHasFileParameter(constructor))
//                    throw new IllegalStateException()
//            }
        then:
            thrown HangOutConversationParser.ParseableFileNotPresent
    }

    void "There's a constructor which receives a File"(){
        when:
            def constructors = HangOutConversationParser.getDeclaredConstructors()
            def constructorWithFileParameter
            constructors.each { constructor ->
                if(doesConstructorHasFileParameter(constructor))
                    constructorWithFileParameter = constructor
            }
        then:
            constructorWithFileParameter != null
    }


    private boolean doesConstructorHasFileParameter(Constructor<?> constructor) {
        def existMethodName = constructor.properties.name.equals("jorgwel.googlehangoutsparser.HangOutConversationParser")
        def hasOneParameter = constructor.properties.genericParameterTypes.size() == 1
        def parametersAreCorrect = constructor.properties.genericParameterTypes.equals([String.class])
        return existMethodName && hasOneParameter && parametersAreCorrect
    }


}
