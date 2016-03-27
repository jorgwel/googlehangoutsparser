package jorgwel.googlehangoutsparser
/**
 * Created by jorgwel on 27/03/16.
 */
class HangOutConversationParser {
    public HangOutConversationParser() {
        throw new ParseableFileNotPresent()
    }
    
    public HangOutConversationParser(File conversationContainerFile) {
    }
    
    static class ParseableFileNotPresent extends RuntimeException {
        public ParseableFileNotPresent() {
            super("File to be used to extract google hangouts conversation, not present")
        }
    }
    
}
