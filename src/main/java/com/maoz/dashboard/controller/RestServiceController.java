package com.maoz.dashboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.maoz.dashboard.bean.ShortestPathBean;
import com.maoz.dashboard.entity.Station;
import com.maoz.dashboard.service.astar.WorkBench;

@RestController
@RequestMapping(value = "/service")
public class RestServiceController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WorkBench wbService;
	 
	@RequestMapping(value = "/findpath", method = RequestMethod.POST)
	@ResponseBody
	public ShortestPathBean findpath(HttpServletRequest request, @RequestBody ShortestPathBean shortestPathBean) throws Exception {
		ShortestPathBean result = new ShortestPathBean();
		
		Station source = shortestPathBean.getOrigin();
		Station destination = shortestPathBean.getDestination();
		
		log.info("finding between "+source.getName() +" - "+destination.getName());
		
		result = wbService.CalculateShortestPath(source, destination, shortestPathBean.getType());
		return result;
	}
	
	@RequestMapping(value = "/metadata/reload", method = RequestMethod.HEAD)
	public void reload(HttpServletRequest request) throws Exception {
		 wbService.settingData();
	}
}
