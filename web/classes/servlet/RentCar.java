package servlet;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import transactions.dao.CarDAO;
import transactions.dao.ClientDao;
import transactions.dao.ContractDao;
import transactions.entity.Car;
import transactions.entity.Client;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

/**
 * Created by andrei on 08.12.17.
 */
@WebServlet(value = "/rent", name = "RentCar")
public class RentCar extends HttpServlet {
    private static final long serialVersionUID = -4476854437295496718L;

    private void getJson(){
//        ServletRequestWrapper req = new ServletRequestWrapper();
//        req.setCharacterEncoding("UTF-8");
//        StringBuilder jsonString = new StringBuilder(req.getReader().lines().collect(joining("\n")));
        JSONParser jsonParser = new JSONParser();
        JSONObject resultJson;
//        try {
//            resultJson = (JSONObject) jsonParser.parse(jsonString.toString());
//            String startDate = (String) resultJson.get("start_date");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//            Date start_date = format.parse(startDate);
//            int carId = (int) resultJson.get("carId");
            CarDAO carDAO = CarDAO.getINSTANCE();
//            Car car = carDAO.getCarById(carId).get();

            ClientDao client = ClientDao.getInstance();
//            Optional<Client> clientByLogin = client.getClientByLogin(loginname);
//            String outputJson;
//            if (clientByLogin.isPresent()) {
//                resp.setContentType("application/json");
//                resp.setCharacterEncoding("UTF-8");
//                outputJson = "{\"username\":\"Имя пользователя занято!\"}";
//
//            } else {
//                resp.setCharacterEncoding("UTF-8");
//                resp.setContentType("application/json");
//                outputJson = "{\"username\":\"Имя пользователя свободно!\"}";
//            }
//            resp.getWriter().write(outputJson);
//            System.out.println(loginname);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (java.text.ParseException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String start_date = req.getParameter("start_datetime").replaceAll("([T]+)", " ");
        String end_date = req.getParameter("end_datetime").replaceAll("([T+])", " ");
        int idCar = Integer.parseInt(req.getParameter("carId"));
        int userId = Integer.parseInt(req.getParameter("clientId"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ContractDao contract = ContractDao.getInstance();
        try {
            Timestamp start = new Timestamp(format.parse(start_date).getTime());
            Timestamp end = new Timestamp(format.parse(end_date).getTime());

            contract.createContractByCarIdAndUserId(idCar, userId,start, end);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        super.doPost(req, resp);
    }
}
