package com.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.app.util.Sudoku;



@RestController
@EnableWebMvc
public class ServiceController  extends BaseController{

	
	private static final Logger log = LoggerFactory.getLogger(ServiceController.class);

    @RequestMapping(value="/sudoku",method=RequestMethod.GET)
    public @ResponseBody Object solveSudoku(@RequestParam String input) {
    	log.info("received message in solveSudoku Api ");
    	
    	try {
			if (input == null || input.equals(""))
				return failResponse();
			String solution = Sudoku.solveSudoku(input);
			if (solution == null)
				return failResponse();
			return success(solution);
		} catch (Exception e) {
			return failResponse();
		}
    }
        
}
