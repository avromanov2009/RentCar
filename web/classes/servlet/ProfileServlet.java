package servlet;

import transactions.dao.CarDAO;
import transactions.dao.ContractDao;
import transactions.dao.ViewCarsDao;
import transactions.entity.Car;
import transactions.entity.Client;
import transactions.entity.Contract;
import transactions.entity.FullCarInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrei on 08.12.17.
 */
@WebServlet(value = "/profile", name = "ProfileServlet")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 4906826824570723663L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ContractDao contractDao = ContractDao.getInstance();
        HttpSession session = req.getSession();
        Client client = (Client) session.getAttribute("client");
        List<Contract> contracts = null;
        if (client.getRole_id() == 1) {
            contracts = contractDao.getAllContractsNoConfirmed().get();
        } else {
            contracts = contractDao.getAllContractsByUserId(client.getId_client()).get();
        }

        ViewCarsDao carsDao = ViewCarsDao.getInstance();
        List<FullCarInfo> cars = new ArrayList<>();
        for (Contract contract : contracts) {
            cars.add(carsDao.getFullInfoByCarId(contract.getCar_id()).get());
        }

        req.setAttribute("contracts", contracts);
        req.setAttribute("cars", cars);
        getServletContext().getRequestDispatcher("/profile.jsp").forward(req, resp);
    }
}
