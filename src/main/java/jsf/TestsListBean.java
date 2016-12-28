package jsf;

import dao.TestDAO;
import entities.Test;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.TransactionScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by tanya on 2016-11-28.
 */
@ManagedBean
@RequestScoped
@Log4j2
public class TestsListBean implements Serializable {

    @EJB
    private TestDAO testDAO;

    @Getter
    List<Test> tests;

    @Getter
    List<String> categories;

    @PostConstruct
    public void init() {
        tests = testDAO.findAll();
        categories = testDAO.findAllCategories();
        categories.add("All");
    }

    @Getter @Setter
    private String selectedCategory = "All";

    public void filter() {
        if (selectedCategory.equals("All")) {
            tests = testDAO.findAll();
        } else {
            tests = testDAO.findTestsByCategory(selectedCategory);
        }
    }

    public void delete(int test_id) throws IOException {
        testDAO.delete(testDAO.find(test_id));
        log.info("test deleted. ID: "+test_id);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
}
