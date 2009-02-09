package airline.filter;

import airline.servlet.enumeration.Action;
import airline.servlet.enumeration.Context;
import airline.tables.context.action.*;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

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

    private void testFails(List<String> fails) throws IOException, ServletException {

        Filter urlrewrite = new TableURLRewritingFilter();
        for (String fail : fails) {
            req.setServletPath(fail);
            urlrewrite.doFilter(req, resp, filterChain);
            assertNull(req.getAttribute("url.context"));
            assertNull(req.getAttribute("url.action"));
            assertNull(req.getAttribute("url.table"));
            assertNull(req.getAttribute("url.row"));
            assertNull(req.getAttribute("url.field"));
            assertNull(req.getAttribute("url.handler"));
            assertFalse(filterChain.hasChained());
            assertEquals(resp.getError(), HttpServletResponse.SC_NOT_FOUND);
            req.reset();
            resp.reset();
            filterChain.reset();
        }
    }


    @Test
    public void testTable() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        List<String> tests = new ArrayList<String>();
        List<String> fails = new ArrayList<String>();

        tests.add("/admin/table/");
        tests.add("/admin/table");

        //fails.add("");

        for (String test : tests) {
            req.setServletPath(test);
            urlrewrite.doFilter(req, resp, filterChain);
            assertEquals(Context.TABLES, req.getAttribute("url.context"));
            assertEquals(Action.SHOW, req.getAttribute("url.action"));
            assertNull(req.getAttribute("url.table"));
            assertNull(req.getAttribute("url.row"));
            assertNull(req.getAttribute("url.field"));
            assertTrue(req.getAttribute("url.handler") instanceof ShowTables);
            assertTrue(filterChain.hasChained());

            req.reset();
            resp.reset();
            filterChain.reset();
        }

        testFails(fails);
    }

    @Test
    public void testTableAdd() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        List<String> tests = new ArrayList<String>();
        List<String> fails = new ArrayList<String>();

        tests.add("/admin/table/add");

        fails.add("/admin/table/graou/add");
        fails.add("/admin/table/delete");
        fails.add("/admin/table/edit");

        for (String test : tests) {
            req.setServletPath(test);
            urlrewrite.doFilter(req, resp, filterChain);
            assertEquals(Context.TABLE, req.getAttribute("url.context"));
            assertEquals(Action.ADD, req.getAttribute("url.action"));
            assertNull(req.getAttribute("url.table"));
            assertNull(req.getAttribute("url.row"));
            assertNull(req.getAttribute("url.field"));
            assertTrue(req.getAttribute("url.handler") instanceof AddTable);
            assertTrue(filterChain.hasChained());
            req.reset();
            resp.reset();
            filterChain.reset();
        }

        testFails(fails);
    }

    @Test
    public void testTable_() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        List<String> tests = new ArrayList<String>();
        List<String> fails = new ArrayList<String>();

        tests.add("/admin/table/graou/");

        fails.add("/admin/table/graou");

        for (String test : tests) {
            req.setServletPath(test);
            urlrewrite.doFilter(req, resp, filterChain);
            assertEquals(Context.TABLE, req.getAttribute("url.context"));
            assertEquals(Action.SHOW, req.getAttribute("url.action"));
            assertEquals("graou", req.getAttribute("url.table"));
            assertNull(req.getAttribute("url.row"));
            assertNull(req.getAttribute("url.field"));
            assertTrue(req.getAttribute("url.handler") instanceof ShowTable);
            assertTrue(filterChain.hasChained());
            req.reset();
            resp.reset();
            filterChain.reset();
        }

        testFails(fails);
    }

    @Test
    public void testTable_delete() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        List<String> tests = new ArrayList<String>();
        List<String> fails = new ArrayList<String>();

        tests.add("/admin/table/graou/delete");

        fails.add("/admin/table/graou/show");
        fails.add("/admin/table/graou/add");

        for (String test : tests) {
            req.setServletPath(test);
            urlrewrite.doFilter(req, resp, filterChain);
            assertEquals(Context.TABLE, req.getAttribute("url.context"));
            assertEquals(Action.DELETE, req.getAttribute("url.action"));
            assertEquals("graou", req.getAttribute("url.table"));
            assertNull(req.getAttribute("url.row"));
            assertNull(req.getAttribute("url.field"));
            assertTrue(req.getAttribute("url.handler") instanceof DeleteTable);
            assertTrue(filterChain.hasChained());
            req.reset();
            resp.reset();
            filterChain.reset();
        }

        testFails(fails);
    }

    @Test
    @Ignore // pas encore implémenté
    public void testTable_edit() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        List<String> tests = new ArrayList<String>();
        List<String> fails = new ArrayList<String>();

        tests.add("/admin/table/graou/edit");

        fails.add("/admin/table/graou/show");
        fails.add("/admin/table/graou/add");

        for (String test : tests) {
            req.setServletPath(test);
            urlrewrite.doFilter(req, resp, filterChain);
            assertEquals(Context.TABLE, req.getAttribute("url.context"));
            assertEquals(Action.EDIT, req.getAttribute("url.action"));
            assertEquals("graou", req.getAttribute("url.table"));
            assertNull(req.getAttribute("url.row"));
            assertNull(req.getAttribute("url.field"));
            //assertTrue(req.getAttribute("url.handler") instanceof EditTable);
            assertTrue(filterChain.hasChained());
            req.reset();
            resp.reset();
            filterChain.reset();
        }

        testFails(fails);
    }

    @Test
    public void testTable_rowAdd() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        List<String> tests = new ArrayList<String>();
        List<String> fails = new ArrayList<String>();

        tests.add("/admin/table/graou/row/add");

        fails.add("/admin/table/graou/row/1234/add");
        fails.add("/admin/table/graou/row/edit");
        fails.add("/admin/table/graou/row/delete");

        for (String test : tests) {
            req.setServletPath(test);
            urlrewrite.doFilter(req, resp, filterChain);
            assertEquals(Context.ROW, req.getAttribute("url.context"));
            assertEquals(Action.ADD, req.getAttribute("url.action"));
            assertEquals("graou", req.getAttribute("url.table"));
            assertNull(req.getAttribute("url.row"));
            assertNull(req.getAttribute("url.field"));
            assertTrue(req.getAttribute("url.handler") instanceof AddRow);
            assertTrue(filterChain.hasChained());
            req.reset();
            resp.reset();
            filterChain.reset();
        }

        testFails(fails);
    }

    @Test
    public void testTable_row_edit() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        List<String> tests = new ArrayList<String>();
        List<String> fails = new ArrayList<String>();

        tests.add("/admin/table/graou/row/1234/edit");

        fails.add("/admin/table/graou/row/l2EA/edit");

        for (String test : tests) {
            req.setServletPath(test);
            urlrewrite.doFilter(req, resp, filterChain);
            assertEquals(Context.ROW, req.getAttribute("url.context"));
            assertEquals(Action.EDIT, req.getAttribute("url.action"));
            assertEquals("graou", req.getAttribute("url.table"));
            assertEquals("1234", req.getAttribute("url.row"));
            assertNull(req.getAttribute("url.field"));
            assertTrue(req.getAttribute("url.handler") instanceof EditRow);
            assertTrue(filterChain.hasChained());
            req.reset();
            resp.reset();
            filterChain.reset();
        }

        testFails(fails);
    }

    @Test
    public void testTable_row_delete() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        List<String> tests = new ArrayList<String>();
        List<String> fails = new ArrayList<String>();

        tests.add("/admin/table/graou/row/1234/delete");

        fails.add("/admin/table/graou/row/l2EA/delete");

        for (String test : tests) {
            req.setServletPath(test);
            urlrewrite.doFilter(req, resp, filterChain);
            assertEquals(Context.ROW, req.getAttribute("url.context"));
            assertEquals(Action.DELETE, req.getAttribute("url.action"));
            assertEquals("graou", req.getAttribute("url.table"));
            assertEquals("1234", req.getAttribute("url.row"));
            assertNull(req.getAttribute("url.field"));
            assertTrue(req.getAttribute("url.handler") instanceof DeleteRow);
            assertTrue(filterChain.hasChained());
            req.reset();
            resp.reset();
            filterChain.reset();
        }

        testFails(fails);
    }

    @Test
    public void testTable_fieldAdd() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        List<String> tests = new ArrayList<String>();
        List<String> fails = new ArrayList<String>();

        tests.add("/admin/table/graou/field/add");

        fails.add("/admin/table/graou/field/1234/add");
        fails.add("/admin/table/graou/field/edit");
        fails.add("/admin/table/graou/field/delete");

        for (String test : tests) {
            req.setServletPath(test);
            urlrewrite.doFilter(req, resp, filterChain);
            assertEquals(Context.FIELD, req.getAttribute("url.context"));
            assertEquals(Action.ADD, req.getAttribute("url.action"));
            assertEquals("graou", req.getAttribute("url.table"));
            assertNull(req.getAttribute("url.row"));
            assertNull(req.getAttribute("url.field"));
            assertTrue(req.getAttribute("url.handler") instanceof AddField);
            assertTrue(filterChain.hasChained());
            req.reset();
            resp.reset();
            filterChain.reset();
        }

        testFails(fails);
    }

    @Test
    public void testTable_field_edit() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        List<String> tests = new ArrayList<String>();
        List<String> fails = new ArrayList<String>();

        tests.add("/admin/table/graou/field/fieldName/edit");

        //fails.add("");

        for (String test : tests) {
            req.setServletPath(test);
            urlrewrite.doFilter(req, resp, filterChain);
            assertEquals(Context.FIELD, req.getAttribute("url.context"));
            assertEquals(Action.EDIT, req.getAttribute("url.action"));
            assertEquals("graou", req.getAttribute("url.table"));
            assertEquals("fieldName", req.getAttribute("url.field"));
            assertNull(req.getAttribute("url.row"));
            assertTrue(req.getAttribute("url.handler") instanceof EditField);
            assertTrue(filterChain.hasChained());
            req.reset();
            resp.reset();
            filterChain.reset();
        }

        testFails(fails);
    }

    @Test
    public void testTable_field_delete() throws IOException, ServletException {
        Filter urlrewrite = new TableURLRewritingFilter();
        List<String> tests = new ArrayList<String>();
        List<String> fails = new ArrayList<String>();

        tests.add("/admin/table/graou/field/fieldName/delete");

        //fails.add("");

        for (String test : tests) {
            req.setServletPath(test);
            urlrewrite.doFilter(req, resp, filterChain);
            assertEquals(Context.FIELD, req.getAttribute("url.context"));
            assertEquals(Action.DELETE, req.getAttribute("url.action"));
            assertEquals("graou", req.getAttribute("url.table"));
            assertEquals("fieldName", req.getAttribute("url.field"));
            assertNull(req.getAttribute("url.row"));
            assertTrue(req.getAttribute("url.handler") instanceof DeleteField);
            assertTrue(filterChain.hasChained());
            req.reset();
            resp.reset();
            filterChain.reset();
        }

        testFails(fails);
    }

}
