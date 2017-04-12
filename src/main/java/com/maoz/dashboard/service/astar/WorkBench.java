package com.maoz.dashboard.service.astar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.maoz.dashboard.bean.ShortestPathBean;
import com.maoz.dashboard.entity.Path;
import com.maoz.dashboard.entity.Station;
import com.maoz.dashboard.repository.PathRepository;
import com.maoz.dashboard.repository.StationRepository;


@Component
public class WorkBench {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	List<Path> pathList;
	List<Station> stationList;
	
	Map<Long, Map<Long, Double>> hueristicDistance; //เอาไว้เก็บฮิวริสติก
	Map<Long, Map<Long, Double>> hueristicExchange;
	
	GraphAStar<Long> graphDistance;
	GraphAStar<Long> graphExchange;
	
	AStar<Long> aStarDistance;
	AStar<Long> aStarExchange;
	
	@Autowired
	private PathRepository pathRepository;
	
	@Autowired
	private StationRepository stationRepository;
	
	
    @PostConstruct
    public void settingData(){
    	log.info("###################### initial data ######################");
    	List<Path> pathParamList = (List<Path>) pathRepository.findAll();
    	List<Station> stationParamList = (List<Station>) stationRepository.findAll();
    	pathList = pathParamList;
    	stationList = stationParamList;
    	
    	setupGraphDistance();
    	setupGraphExchange();
    }
    
    private void setupGraphDistance(){
    	hueristicDistance = new HashMap<>();
    	
    	for (int i = 0; i<stationList.size();i++){//ลูปสำหรับข้อมูลต้นทาง
            Map<Long, Double> map = new HashMap<>(); //map ของข้อมูล คุ่อันดับ สถานีและระยะขจัด
            for (int j = 0; j<stationList.size();j++){//ลุปสำหรับข้อมูลปลายทาง
                //ถ้าเงื่อนไขเป็นระยะทาง
                
            	Double sLat,sLng,dLat,dLng;
            	Double ans;
                //ต้นทาง
                sLat = Double.valueOf(stationList.get(i).getLat().toString());
                sLng = Double.valueOf(stationList.get(i).getLng().toString());
                //ปลายทาง
                dLat = Double.valueOf(stationList.get(j).getLat().toString());
                dLng = Double.valueOf(stationList.get(j).getLng().toString());
                ans = calculateDistance(sLat, sLng, dLat, dLng);//ส่งไปคำนวณหาระยะทาง
                //ใส่ข้อมูลระยะขจัดให้กับสถานี j
                map.put(stationList.get(j).getId(), ans);//กำหนด Heuristic ให้กับสถานี
                
            }
            hueristicDistance.put(stationList.get(i).getId(),map);
        }
    	
    	graphDistance = new GraphAStar<Long>(hueristicDistance);//สร้างกราฟ AStar โดยกำหนดข้อมูล ฮิวริสติกไปด้วย
        //เพิ่มโหนดให้กราฟ
        for (int i = 0; i < stationList.size();i++){
        	graphDistance.addNode(stationList.get(i).getId());//เอาชื่อสถานีไปเป็น node
        }
        
        //เพิ่มเส้นเชื่อมให้โหนดในกราฟ
        for (int i = 0; i < pathList.size();i++){
        	graphDistance.addEdge(pathList.get(i).getOriginId(), pathList.get(i).getDestId() ,pathList.get(i).getDistance());//เพิ่มเส้นเชื่อมระหว่างสถานี
        }
        
        aStarDistance = new AStar<>(graphDistance);
    
    }
    
    private void setupGraphExchange(){
    	hueristicExchange = new HashMap<>();
    	
    	for (int i = 0; i<stationList.size();i++){//ลูปสำหรับข้อมูลต้นทาง
            Map<Long, Double> map = new HashMap<>(); //map ของข้อมูล คุ่อันดับ สถานีและระยะขจัด
            for (int j = 0; j<stationList.size();j++){//ลุปสำหรับข้อมูลปลายทาง
                //ถ้าเงื่อนไขเป็นระยะทาง
                String[] s,d;
                double ans;
                //ต้นทาง
                s = stationList.get(i).getType().split("_");
                //ปลายทาง
                d = stationList.get(j).getType().split("_");

                ans = PriceH(s[0],d[0]);
                map.put(stationList.get(j).getId(),ans);
            }
            hueristicExchange.put(stationList.get(i).getId(),map);
        }
    	
    	graphExchange = new GraphAStar<Long>(hueristicExchange);//สร้างกราฟ AStar โดยกำหนดข้อมูล ฮิวริสติกไปด้วย
        //เพิ่มโหนดให้กราฟ
        for (int i = 0; i < stationList.size();i++){
        	graphExchange.addNode(stationList.get(i).getId());//เอาชื่อสถานีไปเป็น node
        }
        
        for (int i = 0; i < pathList.size();i++){

            if (pathList.get(i).getType().equals("WALK")){
            	graphExchange.addEdge(pathList.get(i).getOriginId(),pathList.get(i).getDestId(),1.0);
            }else{
            	graphExchange.addEdge(pathList.get(i).getOriginId(),pathList.get(i).getDestId(),0.0);
            }
         }
        
        aStarExchange = new AStar<>(graphExchange);
    }
    
	/**คำนวณหาค่า ฮิวริสติก ของราคา โดยใช้การการเปลี่ยนสถานี
     * @param source ประเภทสถานีต้นทาง
     * @param destination ประเภทสถานีปลายทาง
     * @return ค่าเปลี่ยนสถานีของต้นทางกับปลายทาง
     * */
    private double PriceH(String source,String destination){//ฮิวริสติกด้านราคา
        double heuristic = 0.0;
        Map<String, Double> BTS = new HashMap<>();
        BTS.put("BTS", 0.0);
        BTS.put("MRT", 1.0);
        BTS.put("ARL", 1.0);
        BTS.put("BRT", 1.0);

        Map<String, Double> BRT = new HashMap<>();
        BRT.put("BRT", 0.0);
        BRT.put("BTS", 1.0);
        BRT.put("MRT", 2.0);
        BRT.put("ARL", 2.0);

        Map<String, Double> MRT = new HashMap<>();
        MRT.put("MRT", 0.0);
        MRT.put("BTS", 1.0);
        MRT.put("BRT", 2.0);
        MRT.put("ARL", 1.0);

        Map<String, Double> ARL = new HashMap<>();
        ARL.put("ARL", 0.0);
        ARL.put("BTS", 1.0);
        ARL.put("MRT", 1.0);
        ARL.put("BRT", 2.0);

        switch (source){
            case "BTS":
                    heuristic = BTS.get(destination);
                break;
            case "BRT":
                    heuristic = BRT.get(destination);
                break;
            case "MRT":
                    heuristic = MRT.get(destination);
                break;
            case "ARL":
                    heuristic = ARL.get(destination);
                break;

        }
        return heuristic;
    }
    /**คำนวณลำดับการเดินทาง
     * @param source ต้นทาง
     * @param destination ปลายทาง
     * @param type ประเภทเงื่อนไข เปลี่ยนสถานีน้อย หรือ ระยะทางสั้น
     * @return ลำดับการเดินทางในรูป ArrayList
     * */
    public ShortestPathBean CalculateShortestPath(Station source, Station destination, int type){
        ShortestPathBean result = new ShortestPathBean(); //เอาไว้รีเทินลำดับการเดินทาง
        result.setOrigin(source);
        result.setDestination(destination);
        
        List<Long> resultPath;
        if (type == 1) {
        	result.setHueristic(hueristicDistance);
        	resultPath = aStarDistance.astar(source.getId(), destination.getId());
        	result.setTotalDistance(aStarDistance.distance);
        } else {
        	result.setHueristic(hueristicExchange);
        	resultPath = aStarExchange.astar(source.getId(), destination.getId());
        	result.setTotalDistance(aStarExchange.distance);
        }
       
        //ข้อมูลสรุป
        if (type == 1){
        	result.setSummary(source.getName() + " to " + destination.getName() + " = " + Math.round(result.getTotalDistance())+" kilometer");
        }else {
        	result.setSummary(source.getName() + " to " + destination.getName());
        }
        result.setDirectionList(resultPath);

        return result;
    }
    
    private Double calculateDistance(Double sLat, Double sLng, Double dLat, Double dLng){//คำนวณระยะทางขจัดระหว่างจุดสองจุดบนโลกใช้ HarvenSine
    	Double AVG_R_EARTH = 6371.00;//km

    	Double latDistance = Math.toRadians(sLat-dLat);
    	Double lngDistance = Math.toRadians(sLng-dLng);
    	Double a = Math.sin(latDistance / 2) * Math.sin(latDistance /2) +
                Math.cos(Math.toRadians(sLat))*Math.cos(Math.toRadians(dLat))*
                        Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);
    	Double c = 2 * Math.atan2(Math.sqrt(a),Math.sqrt(1-a));

        return AVG_R_EARTH * c;
    }
}
