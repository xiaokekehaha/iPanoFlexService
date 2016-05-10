package model;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

public class Conf {
	private ViewConf viewConf;
	private List<SceneConf> scenesConf = new ArrayList<SceneConf>();
	private ConfInfo confInfo;
	
	

	public ConfInfo getConfInfo() {
		return confInfo;
	}

	public void setConfInfo(ConfInfo confInfo) {
		this.confInfo = confInfo;
	}

	public ViewConf getViewConf() {
		return viewConf;
	}
	
	public void setViewConf(ViewConf viewConf) {
		this.viewConf = viewConf;
	}
	
	public void addSceneConf(SceneConf sceneConf){
		scenesConf.add(sceneConf);
	}
	
	public List<SceneConf> getScenesConf() {
		return scenesConf;
	}

	public void setScenesConf(List<SceneConf> scenesConf) {
		this.scenesConf = scenesConf;
	}
	
	public String toJson(){
		JSONObject jsonObject = JSONObject.fromObject(this);
		return jsonObject.toString();
	}
	
	public static Conf getInitConf(){
		Conf conf = new Conf();
		ViewConf viewConf = new ViewConf();
		viewConf.setScenesNum(1);
		viewConf.setSphereRadius(1000);
		viewConf.setSegmentsHeight(50);
		viewConf.setSegmentsWidth(50);
		
		conf.setViewConf(viewConf);
		
		SceneConf sceneConf = new SceneConf();
		sceneConf.setLat(0);
		sceneConf.setLon(0);
		sceneConf.setMaxLat(10);
		sceneConf.setMinLat(-10);
		sceneConf.setNear(1);
		sceneConf.setFar(1000);
		sceneConf.setFov(76);
		sceneConf.setMaxFov(106);
		sceneConf.setMinFov(46);
		sceneConf.setViewPortH(400);
		sceneConf.setViewPortW(700);
		sceneConf.setUrl("/home/xzj/we2.jpg");
		
		List<SceneConf> sceneConfs = new ArrayList<SceneConf>();
		sceneConfs.add(sceneConf);
		
		conf.setScenesConf(sceneConfs);
		
		return conf;
	}



}