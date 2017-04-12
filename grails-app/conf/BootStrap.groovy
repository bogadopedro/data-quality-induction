import javax.servlet.ServletContext

class BootStrap {

    def curiosamenteClient

    def init = { servletContext ->

        println "Starting application with SCOPE: ${System.env.SCOPE}"

        environments {
            development {
//                println "Loading mocks"
//                loadQuestionsApiMocks(servletContext)
            }

            test {
                println "Loading mocks"
                loadQuestionsApiMocks(servletContext)
            }
        }

    }


    def destroy = {
    }

    private void loadQuestionsApiMocks(ServletContext servletContext) {
        if (!servletContext) {
            throw new Exception("Couldn't read from servletContext since its null.")
        }
        def questions = new File(servletContext.getRealPath('resources/mocks/questions/questions.json')).getText()

        curiosamenteClient.mockedQuestions = questions
    }
}
