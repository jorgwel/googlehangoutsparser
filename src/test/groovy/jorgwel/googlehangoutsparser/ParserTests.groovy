package jorgwel.googlehangoutsparser

import spock.lang.Specification

/**
 * Created by jorgwel on 26/03/16.
 */
public class ParserTests extends Specification {

    void "This is a test"() {
        when:

        def file = new File("src/test/resources/conversacion_completa.html")

        def parser = new org.cyberneko.html.parsers.SAXParser()

        new XmlParser(parser).parse(file).with { page ->

            def bodyTag = page.'**'.DIV.grep {
                it.'@class'.toString().contains("fkp8p")
            }

            def contentTag = bodyTag.each {
                it.'@class'.toString().contains("tk TmwRj Sn GjkoZd")
            }

            def all1 = contentTag[0]
            println "all1.size(): " + all1.children().size()
            def messages = ((Node) all1).findAll() {
                it.'@class'.toString().contains("tk TmwRj Sn GjkoZd")
            }

            messages.each { message ->
                Node data = extractMessageData(message)
                Node messageDateInfo = extractMessageDateInfo(data)
                Node text = extractMessageText(data)

                text.children().each {
                    if (it instanceof String)
                        println "TEXT: " + (it.empty ? " -- " : it)
                    else if (it instanceof Node)
                        if (it.name().equals("A"))
                            println "LINK: " + it.attribute("data-display")
                        else if (it.name().equals("I"))
                            println " = I = : " + it.children()[0]
                        else if (it.attribute("data-emo"))
                            println "EMOTICON"
//                        else
//                            println "= SEPAAA =" + it.attributes() + " === " + it


                }
                def arrayDateInfo = messageDateInfo.children()[0].split("]")
                println "DATE: " + arrayDateInfo[0].replace("[", "")
                println "WHO: " + arrayDateInfo[1]
                println ""
                println ""
            }

        }

        then:


        true
    }

    private Node extractMessageDateInfo(Node data) {
        ((Node) data.children()[1])
    }

    private Node extractMessageText(Node data) {
        ((Node) data.children()[2])
    }

    private Node extractMessageData(message) {
        def all3 = ((Node) message.children()[0])
        def all4 = ((Node) all3.children()[0])
        def all5 = ((Node) all4.children()[1])
        def all6 = ((Node) all5.children()[1])
        def messageData = ((Node) all6.children()[0])
        messageData
    }


}
