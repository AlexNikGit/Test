package com.jntsys.view;

import org.springframework.web.servlet.view.AbstractUrlBasedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by alex_nik on 06.07.15.
 */
public class XProcView extends AbstractUrlBasedView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    }

    protected Result createResult(HttpServletResponse response) throws Exception {
        return new StreamResult(response.getOutputStream());
    }
}