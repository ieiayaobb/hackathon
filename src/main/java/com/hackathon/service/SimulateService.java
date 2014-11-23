package com.hackathon.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.hackathon.bean.GraphBean;
import com.hackathon.bean.TreeBean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;

@Service
public class SimulateService {

//	public String getGraph() {
//		System.out.println("Request received............");
//		List<GraphBean> result = new LinkedList<>();
//		String temp = "name0";
//		GraphBean graph = new GraphBean(temp, temp, "building1");
//		graph.addAdjacency(temp, "name1");
//		graph.addAdjacency(temp, "name2");
//		graph.addAdjacency(temp, "name3");
//		result.add(graph);
//
//		temp = "name1";
//		GraphBean graph1 = new GraphBean(temp, temp, "building2");
//		graph1.addAdjacency(temp, "name0");
//		graph1.addAdjacency(temp, "name2");
//		graph1.addAdjacency(temp, "name4");
//		graph1.addAdjacency(temp, "name5");
//		result.add(graph1);
//
//		temp = "name2";
//		GraphBean graph2 = new GraphBean(temp, temp, "building4");
//		graph2.addAdjacency(temp, "name0");
//		graph2.addAdjacency(temp, "name3");
//		result.add(graph2);
//
//		temp = "name3";
//		GraphBean graph3 = new GraphBean(temp, temp, "building8");
//		graph3.addAdjacency(temp, "name0");
//		graph3.addAdjacency(temp, "name1");
//		result.add(graph3);
//
//		temp = "name4";
//		GraphBean graph4 = new GraphBean(temp, temp, "building9");
//		graph4.addAdjacency(temp, "name0");
//		graph4.addAdjacency(temp, "name2");
//		graph4.addAdjacency(temp, "name3");
//		result.add(graph4);
//
//		temp = "name5";
//		GraphBean graph5 = new GraphBean(temp, temp, "building3");
//		result.add(graph5);

//		return JSON.toJSONString(result);
//	}

	@RequestMapping("getTree")
	public String getTree(String id) {
		System.out.println("Request received............" + id);
		
		TreeBean tree = new TreeBean(id, id);
		tree.addChild("name1", "name1");
		tree.addChild("name2", "name2");
		tree.addChild("name3", "name3");

		TreeBean subTree = tree.getChildren().get(0);
		subTree.addChild("name11", "name11");
		subTree.addChild("name12", "name12");
		subTree.addChild("name13", "name13");
		TreeBean subTree2 = subTree.getChildren().get(0);
		subTree2.addChild("name111", "name111");
		subTree2.addChild("name112", "name112");
		subTree2.addChild("name113", "name113");
		subTree2 = subTree.getChildren().get(1);
		subTree2.addChild("name121", "name121");
		subTree2.addChild("name122", "name122");
		subTree2.addChild("name123", "name123");
		subTree2 = subTree.getChildren().get(2);
		subTree2.addChild("name131", "name131");
		subTree2.addChild("name132", "name132");
		subTree2.addChild("name133", "name133");

		subTree = tree.getChildren().get(1);
		subTree.addChild("name21", "name21");
		subTree.addChild("name22", "name22");
		subTree.addChild("name23", "name23");
		subTree2 = subTree.getChildren().get(0);
		subTree2.addChild("name211", "name211");
		subTree2.addChild("name212", "name212");
		subTree2.addChild("name213", "name213");
		subTree2 = subTree.getChildren().get(1);
		subTree2.addChild("name221", "name221");
		subTree2.addChild("name222", "name222");
		subTree2.addChild("name223", "name223");
		subTree2 = subTree.getChildren().get(2);
		subTree2.addChild("name231", "name231");
		subTree2.addChild("name232", "name232");
		subTree2.addChild("name233", "name233");

		subTree = tree.getChildren().get(2);
		subTree.addChild("name31", "name31");
		subTree.addChild("name32", "name32");
		subTree.addChild("name33", "name33");
		subTree2 = subTree.getChildren().get(0);
		subTree2.addChild("name311", "name311");
		subTree2.addChild("name312", "name312");
		subTree2.addChild("name313", "name313");
		subTree2 = subTree.getChildren().get(1);
		subTree2.addChild("name321", "name321");
		subTree2.addChild("name322", "name322");
		subTree2.addChild("name323", "name323");
		subTree2 = subTree.getChildren().get(2);
		subTree2.addChild("name331", "name331");
		subTree2.addChild("name332", "name332");
		subTree2.addChild("name333", "name333");

		return JSON.toJSONString(tree);
	}

}
