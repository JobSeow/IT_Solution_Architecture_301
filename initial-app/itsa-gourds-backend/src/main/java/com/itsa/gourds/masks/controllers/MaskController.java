package com.itsa.gourds.masks.controllers;

import com.itsa.gourds.masks.daos.MaskDAO;
import com.itsa.gourds.masks.entities.Mask;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class MaskController {

    @RequestMapping(path = "/masks/{id}", method = RequestMethod.GET)
    public Mask getRoute(@PathVariable(value = "id") String id) {
        MaskDAO dao = new MaskDAO();

        return dao.getMask(id);
    }

    @RequestMapping(path = "/masks", method = RequestMethod.GET)
    public List<Mask> getRoute() {
        MaskDAO dao = new MaskDAO();

        return dao.getMasks();
    }

    @RequestMapping(path = "/masks", method = RequestMethod.POST)
    public boolean createMask(@RequestBody Mask mask) {
        MaskDAO dao = new MaskDAO();

        return dao.createMask(mask);
    }

    @RequestMapping(path = "/masks/{id}", method = RequestMethod.PUT)
    public boolean updateMask(
        @PathVariable(value = "id") String id,
        @RequestBody Mask mask
    ) {
        MaskDAO dao = new MaskDAO();

        return dao.updateMask(id, mask);
    }
}
