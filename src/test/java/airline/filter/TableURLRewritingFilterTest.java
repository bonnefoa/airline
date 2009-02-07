package airline.filter;

import airline.servlet.enumeration.Action;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: tetradavid
 * Date: Feb 7, 2009
 * Time: 11:29:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class TableURLRewritingFilterTest {

    class ReqMock extends HttpServletRequestMock {
        Map<String, Object> attr = new HashMap<String, Object>();
        private String servletPath;

        @Override
        public void setAttribute(String name, Object o) {
            attr.put(name, o);
        }

        @Override
        public Object getAttribute(String name) {
            return attr.get(name);
        }

        @Override
        public String getServletPath() {
            return servletPath;
        }

        public void setServletPath(String servletPath) {
            this.servletPath = servletPath;
        }

        public void reset() {
            attr.clear();
        }
    }

    class RespMock extends HttpServletResponseMock {
        private int error;

        @Override
        public void sendError(int sc) throws IOException {
            error = sc;
        }

        @Override
        public void reset() {
            error = HttpServletResponse.SC_OK;
        }

        public int getError() {
            return error;
        }
    }

    private FilterChainMock filterChain;
    private ReqMock req;
    private RespMock resp;

    @Before
    public void setUp() {
        filterChain = new FilterChainMock();
        req = new ReqMock();
        resp = new RespMock();
    }

    @After
    public void tearDown() {
        req.reset();
        resp.reset();
        filterChain.reset();
    }

    @Test
    public void testTable() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        List<String> tests = new ArrayList<String>();
        tests.add("/admin/table/");
        tests.add("/admin/table");
        for (String test : tests) {
            req.setServletPath(test);
            urlrewrite.doFilter(req, resp, filterChain);
            assertEquals(req.getAttribute("url.action"), Action.SHOW);
            assertNull(req.getAttribute("url.table"));
            assertNull(req.getAttribute("url.row"));
            assertTrue(filterChain.hasChained());
            req.reset();
            resp.reset();
            filterChain.reset();
        }
    }

    @Test
    public void testTableAdd() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        List<String> tests = new ArrayList<String>();
        tests.add("/admin/table/add");

        for (String test : tests) {
            req.setServletPath(test);
            urlrewrite.doFilter(req, resp, filterChain);
            assertEquals(req.getAttribute("url.action"), Action.ADD);
            assertNull(req.getAttribute("url.table"));
            assertNull(req.getAttribute("url.row"));
            assertTrue(filterChain.hasChained());
            req.reset();
            resp.reset();
            filterChain.reset();
        }
    }

    @Test
    public void testTableAddFail() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        List<String> tests = new ArrayList<String>();
        tests.add("/admin/table/add ");
        //tests.add("/admin/table/add/"); // une table s'appelant add
        tests.add("/admin/table/add/sdf");
        tests.add("/admin/table/tablename/add/");

        for (String test : tests) {
            req.setServletPath(test);
            urlrewrite.doFilter(req, resp, filterChain);
            assertNull(req.getAttribute("url.action"));
            assertNull(req.getAttribute("url.table"));
            assertNull(req.getAttribute("url.row"));
            assertFalse(filterChain.hasChained());
            assertEquals(resp.getError(), HttpServletResponse.SC_NOT_FOUND);
            req.reset();
            resp.reset();
            filterChain.reset();
        }
    }

    @Test
    public void testTableEdit() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        Map<String, String> tests = new HashMap<String, String>();
        tests.put("/admin/table/tablename/edit", "tablename");

        for (Map.Entry<String, String> test : tests.entrySet()) {
            req.setServletPath(test.getKey());
            urlrewrite.doFilter(req, resp, filterChain);
            assertEquals(req.getAttribute("url.action"), Action.EDIT);
            assertEquals(req.getAttribute("url.table"), test.getValue());
            assertNull(req.getAttribute("url.row"));
            assertTrue(filterChain.hasChained());
            req.reset();
            resp.reset();
            filterChain.reset();
        }
    }

    @Test
    public void testTableEditFail() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        Map<String, String> tests = new HashMap<String, String>();
        tests.put("/admin/table/tablename/edit/", "tablename");
        tests.put("/admin/table/table/name/edit", "tablename");
        tests.put("/admin/table/table/name/edit/", "tablename");
        tests.put("/admin/table/tablename/edit/something", "tablename");

        for (Map.Entry<String, String> test : tests.entrySet()) {
            req.setServletPath(test.getKey());
            urlrewrite.doFilter(req, resp, filterChain);
            assertNull(req.getAttribute("url.action"));
            assertNull(req.getAttribute("url.table"));
            assertNull(req.getAttribute("url.row"));
            assertFalse(filterChain.hasChained());
            assertEquals(resp.getError(), HttpServletResponse.SC_NOT_FOUND);
            req.reset();
            resp.reset();
            filterChain.reset();
        }
    }

    @Test
    public void testTableDelete() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        Map<String, String> tests = new HashMap<String, String>();
        tests.put("/admin/table/tablename/delete", "tablename");

        for (Map.Entry<String, String> test : tests.entrySet()) {
            req.setServletPath(test.getKey());
            urlrewrite.doFilter(req, resp, filterChain);
            assertEquals(req.getAttribute("url.action"), Action.DELETE);
            assertEquals(req.getAttribute("url.table"), test.getValue());
            assertNull(req.getAttribute("url.row"));
            assertTrue(filterChain.hasChained());
            req.reset();
            resp.reset();
            filterChain.reset();
        }
    }

    @Test
    public void testTableDeleteFail() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        Map<String, String> tests = new HashMap<String, String>();
        tests.put("/admin/table/tablename/delete/", "tablename");
        tests.put("/admin/table/table/name/delete", "tablename");
        tests.put("/admin/table/table/name/delete/", "tablename");
        tests.put("/admin/table/tablename/delete/something", "tablename");

        for (Map.Entry<String, String> test : tests.entrySet()) {
            req.setServletPath(test.getKey());
            urlrewrite.doFilter(req, resp, filterChain);
            assertNull(req.getAttribute("url.action"));
            assertNull(req.getAttribute("url.table"));
            assertNull(req.getAttribute("url.row"));
            assertFalse(filterChain.hasChained());
            assertEquals(resp.getError(), HttpServletResponse.SC_NOT_FOUND);
            req.reset();
            resp.reset();
            filterChain.reset();
        }
    }


    @Test
    public void testRow() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        List<String> tests = new ArrayList<String>();
        tests.add("/admin/table/foobar/row");
        tests.add("/admin/table/foobar/");
        for (String test : tests) {
            req.setServletPath(test);
            urlrewrite.doFilter(req, resp, filterChain);
            assertEquals(req.getAttribute("url.action"), Action.SHOW);
            assertEquals(req.getAttribute("url.table"), "foobar");
            assertNull(req.getAttribute("url.row"));
            assertTrue(filterChain.hasChained());
            req.reset();
            resp.reset();
            filterChain.reset();
        }
    }

    @Test
    public void testRowFail() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        List<String> tests = new ArrayList<String>();
        tests.add("/admin/table/foobar/row/");
        tests.add("/admin/table/foobar");
        for (String test : tests) {
            req.setServletPath(test);
            urlrewrite.doFilter(req, resp, filterChain);
            assertNull(req.getAttribute("url.action"));
            assertNull(req.getAttribute("url.table"));
            assertNull(req.getAttribute("url.row"));
            assertFalse(filterChain.hasChained());
            assertEquals(resp.getError(), HttpServletResponse.SC_NOT_FOUND);
            req.reset();
            resp.reset();
            filterChain.reset();
        }
    }

    @Test
    public void testRowAdd() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        List<String> tests = new ArrayList<String>();
        tests.add("/admin/table/foobar/row/add");

        for (String test : tests) {
            req.setServletPath(test);
            urlrewrite.doFilter(req, resp, filterChain);
            assertEquals(req.getAttribute("url.action"), Action.ADD);
            assertEquals(req.getAttribute("url.table"), "foobar");
            assertNull(req.getAttribute("url.row"));
            assertTrue(filterChain.hasChained());
            req.reset();
            resp.reset();
            filterChain.reset();
        }
    }

    @Test
    public void testRowAddFail() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        List<String> tests = new ArrayList<String>();
        tests.add("/admin/table/foobar/row/add/");
        tests.add("/admin/table/foobar/row/add ");
        tests.add("/admin/table/foobar/row/add/sdf");
        tests.add("/admin/table/foobar/row/tablename/add/");

        for (String test : tests) {
            req.setServletPath(test);
            urlrewrite.doFilter(req, resp, filterChain);
            assertNull(req.getAttribute("url.action"));
            assertNull(req.getAttribute("url.table"));
            assertNull(req.getAttribute("url.row"));
            assertFalse(filterChain.hasChained());
            assertEquals(resp.getError(), HttpServletResponse.SC_NOT_FOUND);
            req.reset();
            resp.reset();
            filterChain.reset();
        }
    }

    @Test
    public void testRowEdit() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        Map<String, String> tests = new HashMap<String, String>();
        tests.put("/admin/table/foobar/row/1234/edit", "1234");

        for (Map.Entry<String, String> test : tests.entrySet()) {
            req.setServletPath(test.getKey());
            urlrewrite.doFilter(req, resp, filterChain);
            assertEquals(req.getAttribute("url.action"), Action.EDIT);
            assertEquals(req.getAttribute("url.table"), "foobar");
            assertEquals(req.getAttribute("url.row"), test.getValue());
            assertTrue(filterChain.hasChained());
            req.reset();
            resp.reset();
            filterChain.reset();
        }
    }

    @Test
    public void testRowEditFail() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        Map<String, String> tests = new HashMap<String, String>();
        tests.put("/admin/table/foobar/row/1234/edit/", "rowid");
        tests.put("/admin/table/foobar/row/12/34/edit", "tablename");
        tests.put("/admin/table/foobar/row/12/34/edit/", "tablename");
        tests.put("/admin/table/foobar/row/12/34/edit/something", "tablename");

        for (Map.Entry<String, String> test : tests.entrySet()) {
            req.setServletPath(test.getKey());
            urlrewrite.doFilter(req, resp, filterChain);
            assertNull(req.getAttribute("url.action"));
            assertNull(req.getAttribute("url.table"));
            assertNull(req.getAttribute("url.row"));
            assertFalse(filterChain.hasChained());
            assertEquals(resp.getError(), HttpServletResponse.SC_NOT_FOUND);
            req.reset();
            resp.reset();
            filterChain.reset();
        }
    }

    @Test
    public void testRowDelete() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        Map<String, String> tests = new HashMap<String, String>();
        tests.put("/admin/table/foobar/row/1234/delete", "1234");

        for (Map.Entry<String, String> test : tests.entrySet()) {
            req.setServletPath(test.getKey());
            urlrewrite.doFilter(req, resp, filterChain);
            assertEquals(req.getAttribute("url.action"), Action.DELETE);
            assertEquals(req.getAttribute("url.table"), "foobar");
            assertEquals(req.getAttribute("url.row"), test.getValue());
            assertTrue(filterChain.hasChained());
            req.reset();
            resp.reset();
            filterChain.reset();
        }
    }

    @Test
    public void testRowDeleteFail() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        Map<String, String> tests = new HashMap<String, String>();
        tests.put("/admin/table/foobar/row/1234/delete/", "rowid");
        tests.put("/admin/table/foobar/row/12/34/delete", "tablename");
        tests.put("/admin/table/foobar/row/12/34/delete/", "tablename");
        tests.put("/admin/table/foobar/row/1234/delete/something", "tablename");

        for (Map.Entry<String, String> test : tests.entrySet()) {
            req.setServletPath(test.getKey());
            urlrewrite.doFilter(req, resp, filterChain);
            assertNull(req.getAttribute("url.action"));
            assertNull(req.getAttribute("url.table"));
            assertNull(req.getAttribute("url.row"));
            assertFalse(filterChain.hasChained());
            assertEquals(resp.getError(), HttpServletResponse.SC_NOT_FOUND);
            req.reset();
            resp.reset();
            filterChain.reset();
        }
    }

}
