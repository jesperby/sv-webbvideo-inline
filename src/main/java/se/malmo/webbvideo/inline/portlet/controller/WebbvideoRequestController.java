package se.malmo.webbvideo.inline.portlet.controller;

import static se.malmo.webbvideo.inline.portlet.constants.AppConstants.*;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.portlet.PortletPreferences;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Henrik Rydstedt
 */
@Controller
@RequestMapping("VIEW")
@SessionAttributes("category")
public class WebbvideoRequestController {
    
    @RequestMapping()
    public String showItems(Model model, RenderRequest request, RenderResponse response, PortletPreferences prefs) {
        
        String videoId =  prefs.getValue(VIDEO_ID, null);

        if(videoId != null) {
            model.addAttribute("videoId", videoId);
            return "video";
        }
        
        model.addAttribute("errorText", "No video has been chosen.");
        return "error";
    }
}
