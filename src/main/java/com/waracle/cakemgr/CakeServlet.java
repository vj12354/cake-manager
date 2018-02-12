package com.waracle.cakemgr;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.waracle.cakemgr.dao.CakeDao;
import com.waracle.cakemgr.dao.CakeDaoImpl;
import com.waracle.cakemgr.dao.CakeInitialiser;

@WebServlet("/cakes")
public class CakeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CakeDao dao;

	@Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init started");
        CakeInitialiser initer = new CakeInitialiser();
        try {
			initer.setup();
        } catch (Exception ex) {
             throw new ServletException(ex);
        }
        dao = new CakeDaoImpl();
        System.out.println("init finished");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("[");

        for (CakeEntity entity : dao.getCakes()) {
            resp.getWriter().println("\t{");

            resp.getWriter().println("\t\t\"title\" : " + entity.getTitle() + ", ");
            resp.getWriter().println("\t\t\"desc\" : " + entity.getDescription() + ",");
            resp.getWriter().println("\t\t\"image\" : " + entity.getImage());

            resp.getWriter().println("\t}");
        }

        resp.getWriter().println("]");
    }

}
