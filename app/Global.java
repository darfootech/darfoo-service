import org.omg.CosNaming.NamingContextPackage.NotFound;
import play.*;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.lang.reflect.Method;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.notFound;

/**
 * Created by zjh on 15-1-9.
 */
public class Global extends GlobalSettings {

    //-> internal error
    @Override
    public F.Promise<Result> onError(Http.RequestHeader requestHeader, Throwable throwable) {
        //return super.onError(requestHeader, throwable);
        return F.Promise.<Result>pure(internalServerError("something is wrong"));
    }

    //-> 404 not found
    @Override
    public F.Promise<Result> onHandlerNotFound(Http.RequestHeader requestHeader) {
        //return super.onHandlerNotFound(requestHeader);
        return F.Promise.<Result>pure(notFound("not found"));
    }

    //-> 客户端请求发送的参数出错了
    @Override
    public F.Promise<Result> onBadRequest(Http.RequestHeader requestHeader, String s) {
        //return super.onBadRequest(requestHeader, s);
        return F.Promise.<Result>pure(badRequest("bad request"));
    }

    //-> 全局请求拦截器
    @Override
    public Action onRequest(Http.Request request, Method method) {
        System.out.println("request uri -> " + request.uri());
        System.out.println("request method -> " + method.getName());
        return super.onRequest(request, method);
    }
}
