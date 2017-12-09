package servlet;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import transactions.dao.ClientDao;
import transactions.entity.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

/**
 * Created by andrei on 08.12.17.
 */
@WebServlet(value = "/checkLogin", asyncSupported = true)
public class CheckLogin extends HttpServlet {

    private static final long serialVersionUID = 7681038535071193093L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        StringBuilder jsonString = new StringBuilder(req.getReader().lines().collect(joining("\n")));
//        Enumeration<String> parameterNames = req.getParameterNames();
        JSONParser jsonParser = new JSONParser();
        JSONObject resultJson;
        try {
            resultJson = (JSONObject) jsonParser.parse(jsonString.toString());
            String loginname = (String) resultJson.get("loginname");
            ClientDao client = ClientDao.getInstance();
            Optional<Client> clientByLogin = client.getClientByLogin(loginname);
            String outputJson;
            if (clientByLogin.isPresent()) {
                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                outputJson = "{\"username\":\"Имя пользователя занято!\"}";

            } else {
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("application/json");
                outputJson = "{\"username\":\"Имя пользователя свободно!\"}";
            }
            resp.getWriter().write(outputJson);
            System.out.println(loginname);
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        while (parameterNames.hasMoreElements()){
//            System.out.println(loginname);
//        }
    }
}
