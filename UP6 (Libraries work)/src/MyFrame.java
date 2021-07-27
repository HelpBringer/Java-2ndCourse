import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyFrame extends JFrame {

    private JPanel panel;
    Text3D text3D;
    Light light1;
    Light light2;
    ColoringAttributes bluePointColorAttributes;
    ColoringAttributes yellowPointColorAttributes;
    final double X1=5.0, Y1=0, Z1=-8.5;
    final double X2=-1.0, Y2=1.0, Z2=2.0;

    public MyFrame() {
        super("Hello World!");
        panel = new JPanel(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas = new Canvas3D(config);
        SimpleUniverse universe = new SimpleUniverse(canvas);
        panel.setPreferredSize(new Dimension(700, 650));
        setResizable(false);
        Vector3d firstVector = new Vector3d(-1.0f, 10.0f, 5.0f);
        Color3f first = new Color3f(firstVector);
        Vector3d secondVector = new Vector3d(1.0f, 2.0f, 4.0f);
        Color3f second = new Color3f(secondVector);

        Transform3D transform;

        // Create the root of the branch graph
        BranchGroup objRoot = new BranchGroup();

        // Create a TransformGroup to scale all objects so they
        // appear in the scene.
        TransformGroup sceneTransformGroup = new TransformGroup();
        Transform3D t3d = new Transform3D();
        t3d.setScale(0.35);
        sceneTransformGroup.setTransform(t3d);
        objRoot.addChild(sceneTransformGroup);


        // Create a bounds for the background and lights
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
                100.0);

            // The Material object defines the appearance of an object under illumination.
            // If the Material object in an Appearance object is null, lighting is disabled for all nodes that use that Appearance object.
            Material m = new Material();
            // The Appearance object defines all rendering state that can be set as a component object of a Shape3D node.
            Appearance a = new Appearance();


            m.setLightingEnable(true);
            a.setMaterial(m);

            Font3D f3d = new Font3D(new Font("Font", Font.BOLD, 1),
                    new FontExtrusion());

            text3D = new Text3D(f3d, "", new Point3f(-2.5f, 0.9f, -2.5f));
            text3D.setCapability(Text3D.ALLOW_STRING_WRITE);
            text3D.setCapability(Text3D.ALLOW_STRING_READ);

            // The Shape3D leaf node specifies all geometric objects.
            // It contains a list of one or more Geometry component objects and a single Appearance component object.
            // The geometry objects define the shape node's geometric data.
            // The appearance object specifies that object's appearance attributes, including color, material, texture, and so on.
            Shape3D s3D1 = new Shape3D();
            s3D1.setGeometry(text3D);
            s3D1.setAppearance(a);

            // Create a transform group node and initialize it to the
            // identity. Enable the TRANSFORM_WRITE capability so that
            // our behavior code can modify it at runtime.
            TransformGroup textTransformGroup = new TransformGroup();

            textTransformGroup.addChild(s3D1);
            sceneTransformGroup.addChild(textTransformGroup);

            //Create the transform group node for the each light and initialize
        // it to the identity. Enable the TRANSFORM_WRITE capability so that
        // our behavior code can modify it at runtime. Add them to the root
        // of the subgraph.

        // Create transformations for the positional lights
        transform = new Transform3D();
        Vector3d bluePointPos = new Vector3d(X1, Y1, Z1);
        transform.set(bluePointPos);
        TransformGroup bluePointTransformGroup = new TransformGroup(transform);
        sceneTransformGroup.addChild(bluePointTransformGroup);


        transform = new Transform3D();
        Vector3d yellowPointPos = new Vector3d(X2, Y2, Z2);
        transform.set(yellowPointPos);
        TransformGroup yellowPointTransformGroup = new TransformGroup(transform);
        sceneTransformGroup.addChild(yellowPointTransformGroup);

        // Create Geometry for point lights
        bluePointColorAttributes = new ColoringAttributes();
        bluePointColorAttributes.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
        yellowPointColorAttributes = new ColoringAttributes();
        yellowPointColorAttributes.setCapability(ColoringAttributes.ALLOW_COLOR_WRITE);
        bluePointColorAttributes.setColor(first);
        yellowPointColorAttributes.setColor(second);
        Appearance appL1 = new Appearance();
        Appearance appL2 = new Appearance();
        appL1.setColoringAttributes(bluePointColorAttributes);
        appL2.setColoringAttributes(yellowPointColorAttributes);
        bluePointTransformGroup.addChild(new Sphere(0.15f, appL1));
        yellowPointTransformGroup.addChild(new Sphere(0.06f, appL2));


        // Create lights


        Point3f lPoint = new Point3f(bluePointPos);
        Point3f rPoint = new Point3f(yellowPointPos);
        //затухание
        Point3f attenuation = new Point3f(1.0f, 0.0f, 0.0f);

        light1 = new PointLight(first, lPoint, attenuation);
        light1.setCapability(Light.ALLOW_COLOR_WRITE);
        light1.setCapability(Light.ALLOW_COLOR_READ);

        light2 = new PointLight(second, rPoint, attenuation);
        light2.setCapability(Light.ALLOW_COLOR_WRITE);
        light2.setCapability(Light.ALLOW_COLOR_READ);

        // Set the influencing bounds

        light1.setInfluencingBounds(bounds);
        light2.setInfluencingBounds(bounds);

        // Add the lights into the scene graph

        bluePointTransformGroup.addChild(light1);
        yellowPointTransformGroup.addChild(light2);

        // Let Java 3D perform optimizations on this scene graph.
        objRoot.compile();


        panel.add(canvas);

        universe.getViewingPlatform().setNominalViewingTransform();
        universe.getViewer().getView().setBackClipDistance(100.0);

        universe.addBranchGraph(objRoot);

        add(panel);

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (Integer.compare(e.getExtendedKeyCode(), KeyEvent.VK_BACK_SPACE) == 0) {
                    if(!text3D.getString().equals("")) {
                        String newText = text3D.getString().substring(0, text3D.getString().length() - 1);
                        text3D.setString(newText);
                    }
                } else if (e.getExtendedKeyCode() == KeyEvent.VK_HOME) {
                    Color color = JColorChooser.showDialog(null, "Choose color for left light!", Color.black);
                    if (color != null) {
                        light1.setColor(new Color3f(color));
                        bluePointColorAttributes.setColor(new Color3f(color));
                    }
                } else if (e.getExtendedKeyCode() == KeyEvent.VK_END) {
                    Color color = JColorChooser.showDialog(null, "Choose color for right light!", Color.black);
                    if (color != null) {
                        light2.setColor(new Color3f(color));
                        yellowPointColorAttributes.setColor(new Color3f(color));
                    }
                } else {
                    String newText = String.valueOf(e.getKeyChar());
                    text3D.setString(text3D.getString() + newText);
                }

            }
        });


        setVisible(true);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        new MyFrame();
    }
}