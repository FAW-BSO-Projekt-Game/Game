package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.BulletAppState.*;
import com.jme3.bullet.PhysicsSpace;
import com.jme3.bullet.objects.PhysicsCharacter;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
//import com.jme3.export.binary.BinaryImporter;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.FogFilter;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.terrain.Terrain;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sun.misc.Version.println;
//import java.awt.Dialog;

public class Main extends SimpleApplication {
    private BulletAppState bulletAppState;
    private RigidBodyControl landscapeControl;
    private CharacterControl player;
    private Vector3f walkdirection = new Vector3f(0f,0f,0f);
    private boolean right,left,up,down;
 //   private CharacterControl NPC;
    
    public static void main(String[] args) {
//        Dialog.getOwnerlessWindows();
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        initBack();
        initBox(1,2,1,ColorRGBA.Green,7,3,5,"hello world");
        initBox(1,2,3,ColorRGBA.Blue,0,0,0,"hi");
        int hi;
        hi = initTest();
        initCam(0,hi,0);
        initTerrain();
        initPlayer(0.3f,1.8f,2f);
        initKeys();
    }
    public void initBox(float br,float ho, float ti, ColorRGBA color, float posx, float posy,float posz,String name){
        Box name1 = new Box(br,ho,ti);
        Geometry name2 = new Geometry("Box",name1);
        Material mat = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", color);
        mat.setName(name);
        name2.move(posx, posy, posz);
        name2.setMaterial(mat);
        rootNode.attachChild(name2);
        System.out.println(name);
    }
    public void initCam(float posx,float posy, float posz){
        cam.setLocation(new Vector3f(posx,posy,posz));
    }
    public int initTest(){
        int e = 3;
        return e;
    }
    public void initTerrain(){
        Spatial terrain;
//        terrain = assetManager.loadModel("Scenes/Terrain.j3o");
//        landscapeControl = new RigidBodyControl(0f);
    }
    public void initPlayer(float rad, float höhe, float laufHöhe){
        CapsuleCollisionShape playerShape = new CapsuleCollisionShape(rad,höhe);
        player = new CharacterControl(playerShape, laufHöhe);
        player.setFallSpeed(1);
        Vector3f grav = new Vector3f(0f,1f,0f);
        if(Vector3f.isValidVector(grav)){
            System.out.println("hu");
        player.setGravity(grav);
        player.setJumpSpeed(1);
        rootNode.addControl(player);
        }else {
            System.out.println("hi");
        }
    }
    public void initKeys(){
        
    }
    
    
    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
        cam.setLocation(player.getPhysicsLocation());
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    public void initBack() {
    viewPort.setBackgroundColor(ColorRGBA.randomColor());
    /** A white, directional light source */ 
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)).normalizeLocal());
        sun.setColor(ColorRGBA.Yellow);
        rootNode.addLight(sun);
     /** Add fog to a scene */
        FilterPostProcessor fpp= new FilterPostProcessor(assetManager);
        FogFilter fog=new FogFilter();
        fog.setFogColor(new ColorRGBA(0.9f, 0.9f, 0.9f, 1f));
        fog.setFogDistance(125);
        fog.setFogDensity(0.5f);
        fpp.addFilter(fog);
        viewPort.addProcessor(fpp);
    /** Load a model. Uses model and texture from jme3-test-data library! */ 
    //    Spatial teapot = assetManager.loadModel("Models/HotAirBallon/11809_Hot_air_balloon_l2.obj");
    //    Material defaultMat = new Material( assetManager, "Common/MatDefs/Misc/ShowNormals.j3md");
    //    teapot.setMaterial(defaultMat);
    //    rootNode.attachChild(teapot);
    /** Load a Node from a .j3o file *//*
    String userHome = System.getProperty("user.home");
    BinaryImporter importer = BinaryImporter.getInstance();
    importer.setAssetManager(assetManager);
    File file = new File(userHome+"/somefile.j3o");
    try {
    Node loadedNode = (Node)importer.load(file);
    loadedNode.setName("loaded node");
    rootNode.attachChild(loadedNode);
    }  catch (IOException ex) {
    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "No saved node loaded.", ex);
    } */

    }
}

