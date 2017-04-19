class UrlMappings {

	static mappings = {

        "400"(controller:'error', action:'treatExceptions')
        "401"(controller:'error', action:'treatExceptions')
        "403"(controller:'error', action:'treatExceptions')
        "404"(controller:'error', action:'treatExceptions')
        "500"(controller:'error', action:'treatExceptions')

        "/checkSentence"(controller: "checkSpeller", parseRequest: true) {
            action = [
                    GET: "methodNotAllowed",
                    POST:"checkSentence",
                    PUT:"methodNotAllowed",
                    DELETE:"methodNotAllowed"
            ]
        }

        "/checkQuestions"(controller: "checkSpeller", parseRequest: true) {
            action = [
                    GET: "methodNotAllowed",
                    POST:"checkQuestions",
                    PUT:"methodNotAllowed",
                    DELETE:"methodNotAllowed"
            ]
        }

        "/words"(controller: "word", parseRequest: true) {
            action = [
                    GET: "methodNotAllowed",
                    POST:"addWord",
                    PUT:"methodNotAllowed",
                    DELETE:"methodNotAllowed"
            ]
        }

        "/ping"(controller: "ping", parseRequest: true) {
            action = [
                    GET: "ping",
                    POST:"methodNotAllowed",
                    PUT:"methodNotAllowed",
                    DELETE:"methodNotAllowed"
            ]
        }

	}
}
