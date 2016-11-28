package jsf;

import dao.TestDAO;
import entities.Test;

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
@TransactionScoped
public class TestsListBean implements Serializable {
    @EJB
    private TestDAO testDAO;

    List<Test> tests;
    List<String> categories;

    @PostConstruct
    public void init() {
        tests = testDAO.findAll();
        categories = testDAO.findAllCategories();
        categories.add("All");
    }

    private String selectedCategory;

    public void filter() {
        if (selectedCategory.equals("All")) {
            tests = testDAO.findAll();
        } else {
            tests = testDAO.findTestsByCategory(selectedCategory);
        }
    }

    public List<Test> getTests() {
        return tests;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public void delete(int test_id) throws IOException {
        testDAO.delete(testDAO.find(test_id));
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }
}
