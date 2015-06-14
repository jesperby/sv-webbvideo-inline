package se.malmo.webbvideo.inline.portlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import se.malmo.webbvideo.inline.portlet.model.WebvideoRequestForm;

import javax.portlet.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static se.malmo.webbvideo.inline.portlet.constants.AppConstants.*;

/**
 *
 * @author Henrik Rydstedt
 */
@Controller
@RequestMapping("CONFIG")
public class WebbvideoConfigController {

    @RequestMapping
    public String doConfig(Model model, PortletPreferences prefs, RenderRequest request, RenderResponse response) {
        
        WebvideoRequestForm form = (WebvideoRequestForm)model.asMap().get("form");
        if (form == null){
            prefs.getMap();
            String videoId = prefs.getValue(VIDEO_ID, "");
            form = new WebvideoRequestForm();
            form.setVideoId(videoId);
        }
        
        model.addAttribute("form", form);
        model.addAttribute("renderResponse", response);
        
        return "config";
    }
    
    @RequestMapping(params = "action=save")
    public void processAction(Model model, @ModelAttribute("form") WebvideoRequestForm form, PortletPreferences prefs, ActionRequest request, ActionResponse response) {        
        model.addAttribute("form", form);
        model.addAttribute("renderResponse", response);
        
        if(request.getParameter("_ok") != null) {
            try {
                prefs.setValue(VIDEO_ID, form.getVideoId().trim());
            } catch (ReadOnlyException ex) {
                Logger.getLogger(WebbvideoConfigController.class.getName()).log(Level.SEVERE, null, ex);
                model.addAttribute("errorText", ex.getMessage());
                response.setRenderParameter("action", "error");
            }
            try {
                prefs.store();
            } catch (IOException ex) {
                Logger.getLogger(WebbvideoConfigController.class.getName()).log(Level.SEVERE, null, ex);
                model.addAttribute("errorText", ex.getMessage());
                response.setRenderParameter("action", "error");
            } catch (ValidatorException ex) {
                Logger.getLogger(WebbvideoConfigController.class.getName()).log(Level.SEVERE, null, ex);
                model.addAttribute("errorText", ex.getMessage());
                response.setRenderParameter("action", "error");
            }
        }
        
        try {
            response.setPortletMode(PortletMode.VIEW);
        } catch (PortletModeException ex) {
            Logger.getLogger(WebbvideoConfigController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
