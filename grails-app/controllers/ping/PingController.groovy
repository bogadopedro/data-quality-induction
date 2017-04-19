package ping

import com.ml.exceptions.MethodNotAllowedException

class PingController {

    def ping = {
        render 'pong'
    }

    def methodNotAllowed = {
        throw new MethodNotAllowedException("Method not allowed")
    }

}
