package com.example.theseus.teleprompter.fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theseus.teleprompter.R;
import com.example.theseus.teleprompter.data.ScriptContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.textSize;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    private String LOG_TAG=DetailActivityFragment.class.getSimpleName();
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.button_play)
    ImageButton button_play;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.seekbar_speed)
    SeekBar seekbar_speed;
    @BindView(R.id.seekbar_text_size)
    SeekBar seekbar_text_size;
    @BindView(R.id.seekbar_line_height)
    SeekBar seekbar_line_height;
    @BindView(R.id.linear_layouts_tool_bar)
    LinearLayout linearLayoutToolBar;
    @BindView(R.id.image_button_setting)
    ImageButton imageButtonSetting;
    @BindView(R.id.linear_layout_seekbar_container)
    LinearLayout linearLayoutSeekbarContainer;
    long time=1000000000;
    int mPlayMode=0;
    int mScrollBy=1;
    public DetailActivityFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_detail, container, false);
        Intent detailIntent=getActivity().getIntent();
        ButterKnife.bind(this,view);
        initializeSeekBars(view);
        if(detailIntent!=null){
//            getActivity().getSupportActionBar().setTitle("Hello world App");
//            content.setText(detailIntent.getStringExtra(ScriptContract.ScriptEntry.COLUMN_CONTENT));
            content.setText("A Nine-patch drawable is a stretchable bitmap image, which Android will automatically resize to accommodate the contents of the view in which you have placed it as the background, e.g. nine-patch background for button, which must stretch to accommodate strings of various lengths. The rules for nine-patch image are following:\n" +
                    "\n" +
                    "Standard PNG image with alpha\n" +
                    "Filename suffix is \".9.png\", e.g. \"btn_login_normal.9.png\"\n" +
                    "Image has an extra 1 pixel wide border, used to define the stretchable/static/padding areas\n" +
                    "Stretchable sections are indicated by 1 px wide black line(s) in the left and top part of the border\n" +
                    "Static sections are indicated by fully transparent or white pixels\n" +
                    "Padding area (optional) is indicated by 1 px wide black line in the right and bottom part of the border In science, buoyancy (pronunciation: /ˈbɔɪ.ənᵗsi/[1][2] or /ˈbuːjənᵗsi/;[1][2] also known as upthrust) is an upward force exerted by a fluid that opposes the weight of an immersed object. In a column of fluid, pressure increases with depth as a result of the weight of the overlying fluid. Thus the pressure at the bottom of a column of fluid is greater than at the top of the column. Similarly, the pressure at the bottom of an object submerged in a fluid is greater than at the top of the object. This pressure difference results in a net upwards force on the object. The magnitude of that force exerted is proportional to that pressure difference, and (as explained by Archimedes' principle) is equivalent to the weight of the fluid that would otherwise occupy the volume of the object, i.e. the displaced fluid.\n" +
                    "\n" +
                    "For this reason, an object whose density is greater than that of the fluid in which it is submerged tends to sink. If the object is either less dense than the liquid or is shaped appropriately (as in a boat), the force can keep the object afloat. This can occur only in a reference frame which either has a gravitational field or is accelerating due to a force other than gravity defining a \"downward\" direction (that is, a non-inertial reference frame). In a situation of fluid statics, the net upward buoyancy force is equal to the magnitude of the weight of fluid displaced by the body.[3]\n" +
                    "\n" +
                    "The center of buoyancy of an object is the centroid of the displaced volume of fluid.\n" +
                    "\n" +
                    "Contents  [hide] \n" +
                    "1\tArchimedes' principle\n" +
                    "2\tForces and equilibrium\n" +
                    "2.1\tSimplified model\n" +
                    "2.2\tStability\n" +
                    "3\tCompressible fluids and objects\n" +
                    "3.1\tCompressible objects\n" +
                    "3.1.1\tSubmarines\n" +
                    "3.1.2\tBalloons\n" +
                    "3.1.3\tDivers\n" +
                    "4\tDensity\n" +
                    "5\tSee also\n" +
                    "6\tReferences\n" +
                    "7\tExternal links\n" +
                    "Archimedes' principle[edit]\n" +
                    "\n" +
                    "A metallic coin (one British pound coin) floats in mercury due to the buoyancy force upon it and appears to float higher because of the surface tension of the mercury.\n" +
                    "Main article: Archimedes' principle\n" +
                    "File:04. Галилеево топче.ogg\n" +
                    "The Galileo's Ball experiment, showing the different buoyancy of the same object, depending on its surrounding medium. The ball has certain buoyancy in water, but once ethanol is added (which is less dense than water), it reduces the density of the medium, thus making the ball sink furter down (reducing its buoyancy). Performed by Prof. Oliver Zajkov at the Physics Institute at the Ss. Cyril and Methodius University of Skopje, Macedonia.\n" +
                    "Archimedes' principle is named after Archimedes of Syracuse, who first discovered this law in 212 B.C.[4] For objects, floating and sunken, and in gases as well as liquids (i.e. a fluid), Archimedes' principle may be stated thus in terms of forces:\n" +
                    "\n" +
                    "Any object, wholly or partially immersed in a fluid, is buoyed up by a force equal to the weight of the fluid displaced by the object.\n" +
                    "\n" +
                    "— Archimedes of Syracuse\n" +
                    "with the clarifications that for a sunken object the volume of displaced fluid is the volume of the object, and for a floating object on a liquid, the weight of the displaced liquid is the weight of the object.\n" +
                    "\n" +
                    "More tersely: Buoyancy = weight of displaced fluid.\n" +
                    "\n" +
                    "Archimedes' principle does not consider the surface tension (capillarity) acting on the body,[5] but this additional force modifies only the amount of fluid displaced, so the principle that Buoyancy = weight of displaced fluid remains valid.\n" +
                    "\n" +
                    "The weight of the displaced fluid is directly proportional to the volume of the displaced fluid (if the surrounding fluid is of uniform density). In simple terms, the principle states that the buoyancy force on an object is equal to the weight of the fluid displaced by the object, or the density of the fluid multiplied by the submerged volume times the gravitational acceleration, g. Thus, among completely submerged objects with equal masses, objects with greater volume have greater buoyancy. This is also known as upthrust.\n" +
                    "\n" +
                    "Suppose a rock's weight is measured as 10 newtons when suspended by a string in a vacuum with gravity acting upon it. Suppose that when the rock is lowered into water, it displaces water of weight 3 newtons. The force it then exerts on the string from which it hangs would be 10 newtons minus the 3 newtons of buoyancy force: 10 − 3 = 7 newtons. Buoyancy reduces the apparent weight of objects that have sunk completely to the sea floor. It is generally easier to lift an object up through the water than it is to pull it out of the water.\n" +
                    "\n" +
                    "Assuming Archimedes' principle to be reformulated as follows,\n" +
                    "\n" +
                    "{\\displaystyle {\\text{apparent immersed weight}}={\\text{weight}}-{\\text{weight of displaced fluid}}\\,} \\text{apparent immersed weight} = \\text{weight} - \\text{weight of displaced fluid}\\,\n" +
                    "then inserted into the quotient of weights, which has been expanded by the mutual volume\n" +
                    "\n" +
                    "{\\displaystyle {\\frac {\\text{density}}{\\text{density of fluid}}}={\\frac {\\text{weight}}{\\text{weight of displaced fluid}}},\\,}  \\frac { \\text{density}} { \\text{density of fluid} } = \\frac { \\text{weight}} { \\text{weight of displaced fluid} }, \\,\n" +
                    "yields the formula below. The density of the immersed object relative to the density of the fluid can easily be calculated without measuring any volumes.:\n" +
                    "\n" +
                    "{\\displaystyle {\\frac {\\text{density of object}}{\\text{density of fluid}}}={\\frac {\\text{weight}}{{\\text{weight}}-{\\text{apparent immersed weight}}}}\\,}  \\frac { \\text {density of object}} { \\text{density of fluid} } = \\frac { \\text{weight}} { \\text{weight} - \\text{apparent immersed weight}}\\,\n" +
                    "(This formula is used for example in describing the measuring principle of a dasymeter and of hydrostatic weighing.)\n" +
                    "\n" +
                    "Example: If you drop wood into water, buoyancy will keep it afloat.\n" +
                    "\n" +
                    "Example: A helium balloon in a moving car. During a period of increasing speed, the air mass inside the car moves in the direction opposite to the car's acceleration (i.e., towards the rear). The balloon is also pulled this way. However, because the balloon is buoyant relative to the air, it ends up being pushed \"out of the way\", and will actually drift in the same direction as the car's acceleration (i.e., forward). If the car slows down, the same balloon will begin to drift backward. For the same reason, as the car goes round a curve, the balloon will drift towards the inside of the curve.\n" +
                    "\n" +
                    "Forces and equilibrium[edit]\n" +
                    "\n" +
                    "This section does not cite any sources. Please help improve this section by adding citations to reliable sources. Unsourced material may be challenged and removed. (January 2016) (Learn how and when to remove this template message)\n" +
                    "This is the equation to calculate the pressure inside a fluid in equilibrium. The corresponding equilibrium equation is:\n" +
                    "\n" +
                    "{\\displaystyle \\mathbf {f} +\\operatorname {div} \\,\\sigma =0}  \\mathbf{f}+\\operatorname{div}\\,\\sigma=0\n" +
                    "where f is the force density exerted by some outer field on the fluid, and σ is the Cauchy stress tensor. In this case the stress tensor is proportional to the identity tensor:\n" +
                    "\n" +
                    "{\\displaystyle \\sigma _{ij}=-p\\delta _{ij}.\\,} \\sigma_{ij}=-p\\delta_{ij}.\\,\n" +
                    "Here δij is the Kronecker delta. Using this the above equation becomes:\n" +
                    "\n" +
                    "{\\displaystyle \\mathbf {f} =\\nabla p.\\,}  \\mathbf{f}=\\nabla p.\\,\n" +
                    "Assuming the outer force field is conservative, that is it can be written as the negative gradient of some scalar valued function:\n" +
                    "\n" +
                    "{\\displaystyle \\mathbf {f} =-\\nabla \\Phi .\\,} \\mathbf{f}=-\\nabla\\Phi.\\,\n" +
                    "Then:\n" +
                    "\n" +
                    "{\\displaystyle \\nabla (p+\\Phi )=0\\Longrightarrow p+\\Phi ={\\text{constant}}.\\,}  \\nabla(p+\\Phi)=0 \\Longrightarrow p+\\Phi = \\text{constant}.\\,\n" +
                    "Therefore, the shape of the open surface of a fluid equals the equipotential plane of the applied outer conservative force field. Let the z-axis point downward. In this case the field is gravity, so Φ = −ρfgz where g is the gravitational acceleration, ρf is the mass density of the fluid. Taking the pressure as zero at the surface, where z is zero, the constant will be zero, so the pressure inside the fluid, when it is subject to gravity, is\n" +
                    "\n" +
                    "{\\displaystyle p=\\rho _{f}gz.\\,} p=\\rho_f g z.\\,\n" +
                    "So pressure increases with depth below the surface of a liquid, as z denotes the distance from the surface of the liquid into it. Any object with a non-zero vertical depth will have different pressures on its top and bottom, with the pressure on the bottom being greater. This difference in pressure causes the upward buoyancy forces.\n" +
                    "\n" +
                    "The buoyancy force exerted on a body can now be calculated easily, since the internal pressure of the fluid is known. The force exerted on the body can be calculated by integrating the stress tensor over the surface of the body which is in contact with the fluid:\n" +
                    "\n" +
                    "{\\displaystyle \\mathbf {B} =\\oint \\sigma \\,d\\mathbf {A} .} \\mathbf{B}=\\oint \\sigma \\, d\\mathbf{A}.\n" +
                    "The surface integral can be transformed into a volume integral with the help of the Gauss theorem:\n" +
                    "\n" +
                    "{\\displaystyle \\mathbf {B} =\\int \\operatorname {div} \\sigma \\,dV=-\\int \\mathbf {f} \\,dV=-\\rho _{f}\\mathbf {g} \\int \\,dV=-\\rho _{f}\\mathbf {g} V} \\mathbf{B}=\\int \\operatorname{div}\\sigma \\, dV = -\\int \\mathbf{f}\\, dV = -\\rho_f \\mathbf{g} \\int\\,dV=-\\rho_f \\mathbf{g} V\n" +
                    "where V is the measure of the volume in contact with the fluid, that is the volume of the submerged part of the body. Since the fluid doesn't exert force on the part of the body which is outside of it.\n" +
                    "\n" +
                    "The magnitude of buoyancy force may be appreciated a bit more from the following argument. Consider any object of arbitrary shape and volume V surrounded by a liquid. The force the liquid exerts on an object within the liquid is equal to the weight of the liquid with a volume equal to that of the object. This force is applied in a direction opposite to gravitational force, that is of magnitude:\n" +
                    "\n" +
                    "{\\displaystyle B=\\rho _{f}V_{\\text{disp}}\\,g,\\,} B = \\rho_f V_\\text{disp}\\, g, \\,\n" +
                    "where ρf is the density of the fluid, Vdisp is the volume of the displaced body of liquid, and g is the gravitational acceleration at the location in question.\n" +
                    "\n" +
                    "If this volume of liquid is replaced by a solid body of exactly the same shape, the force the liquid exerts on it must be exactly the same as above. In other words, the \"buoyancy force\" on a submerged body is directed in the opposite direction to gravity and is equal in magnitude to\n" +
                    "\n" +
                    "{\\displaystyle B=\\rho _{f}Vg.\\,} B = \\rho_f V g. \\,\n" +
                    "The net force on the object must be zero if it is to be a situation of fluid statics such that Archimedes principle is applicable, and is thus the sum of the buoyancy force and the object's weight\n" +
                    "\n" +
                    "{\\displaystyle F_{\\text{net}}=0=mg-\\rho _{f}V_{\\text{disp}}g\\,} F_\\text{net} = 0 = m g - \\rho_f V_\\text{disp} g \\,\n" +
                    "If the buoyancy of an (unrestrained and unpowered) object exceeds its weight, it tends to rise. An object whose weight exceeds its buoyancy tends to sink. Calculation of the upwards force on a submerged object during its accelerating period cannot be done by the Archimedes principle alone; it is necessary to consider dynamics of an object involving buoyancy. Once it fully sinks to the floor of the fluid or rises to the surface and settles, Archimedes principle can be applied alone. For a floating object, only the submerged volume displaces water. For a sunken object, the entire volume displaces water, and there will be an additional force of reaction from the solid floor.\n" +
                    "\n" +
                    "In order for Archimedes' principle to be used alone, the object in question must be in equilibrium (the sum of the forces on the object must be zero), therefore;\n" +
                    "\n" +
                    "{\\displaystyle mg=\\rho _{f}V_{\\text{disp}}g,\\,} mg = \\rho_f V_\\text{disp} g, \\,\n" +
                    "and therefore\n" +
                    "\n" +
                    "{\\displaystyle m=\\rho _{f}V_{\\text{disp}}.\\,} m = \\rho_f V_\\text{disp}.  \\,\n" +
                    "showing that the depth to which a floating object will sink, and the volume of fluid it will displace, is independent of the gravitational field regardless of geographic location.\n" +
                    "\n" +
                    "(Note: If the fluid in question is seawater, it will not have the same density (ρ) at every location. For this reason, a ship may display a Plimsoll line.)\n" +
                    "It can be the case that forces other than just buoyancy and gravity come into play. This is the case if the object is restrained or if the object sinks to the solid floor. An object which tends to float requires a tension restraint force T in order to remain fully submerged. An object which tends to sink will eventually have a normal force of constraint N exerted upon it by the solid floor. The constraint force can be tension in a spring scale measuring its weight in the fluid, and is how apparent weight is defined.\n" +
                    "\n" +
                    "If the object would otherwise float, the tension to restrain it fully submerged is:\n" +
                    "\n" +
                    "{\\displaystyle T=\\rho _{f}Vg-mg.\\,} T = \\rho_f V g - m g . \\,\n" +
                    "When a sinking object settles on the solid floor, it experiences a normal force of:\n" +
                    "\n" +
                    "{\\displaystyle N=mg-\\rho _{f}Vg.\\,} N = m g - \\rho_f V g . \\,\n" +
                    "Another possible formula for calculating buoyancy of an object is by finding the apparent weight of that particular object in the air (calculated in Newtons), and apparent weight of that object in the water (in Newtons). To find the force of buoyancy acting on the object when in air, using this particular information, this formula applies:\n" +
                    "\n" +
                    "'Buoyancy force = weight of object in empty space − weight of object immersed in fluid'\n" +
                    "The final result would be measured in Newtons.\n" +
                    "\n" +
                    "Air's density is very small compared to most solids and liquids. For this reason, the weight of an object in air is approximately the same as its true weight in a vacuum. The buoyancy of air is neglected for most objects during a measurement in air because the error is usually insignificant (typically less than 0.1% except for objects of very low average density such as a balloon or light foam).\n" +
                    "\n" +
                    "Simplified model[edit]\n" +
                    "\n" +
                    "Pressure distribution on an immersed cube\n" +
                    "\n" +
                    "Forces on an immersed cube\n" +
                    "\n" +
                    "Approximation of an arbitrary volume as a group of cubes\n" +
                    "A simplified explanation for the integration of the pressure over the contact area may be stated as follows:\n" +
                    "\n" +
                    "Consider a cube immersed in a fluid with the upper surface horizontal.\n" +
                    "\n" +
                    "The sides are identical in area, and have the same depth distribution, therefore they also have the same pressure distribution, and consequently the same total force resulting from hydrostatic pressure, exerted perpendicular to the plane of the surface of each side.\n" +
                    "\n" +
                    "There are two pairs of opposing sides, therefore the resultant horizontal forces balance in both orthogonal directions, and the resultant force is zero.\n" +
                    "\n" +
                    "The upward force on the cube is the pressure on the bottom surface integrated over its area. The surface is at constant depth, so the pressure is constant. Therefore, the integral of the pressure over the area of the horizontal bottom surface of the cube is the hydrostatic pressure at that depth multiplied by the area of the bottom surface.\n" +
                    "\n" +
                    "Similarly, the downward force on the cube is the pressure on the top surface integrated over its area. The surface is at constant depth, so the pressure is constant. Therefore, the integral of the pressure over the area of the horizontal top surface of the cube is the hydrostatic pressure at that depth multiplied by the area of the top surface.\n" +
                    "\n" +
                    "As this is a cube, the top and bottom surfaces are identical in shape and area, and the pressure difference between the top and bottom of the cube is directly proportional to the depth difference, and the resultant force difference is exactly equal to the weight of the fluid that would occupy the volume of the cube in its absence.\n" +
                    "\n" +
                    "This means that the resultant upward force on the cube is equal to the weight of the fluid that would fit into the volume of the cube, and the downward force on the cube is its weight, in the absence of external forces.\n" +
                    "\n" +
                    "This analogy is valid for variations in the size of the cube.\n" +
                    "\n" +
                    "If two cubes are placed alongside each other with a face of each in contact, the pressures and resultant forces on the sides or parts thereof in contact are balanced and may be disregarded, as the contact surfaces are equal in shape, size and pressure distribution, therefore the buoyancy of two cubes in contact is the sum of the buoyancies of each cube. This analogy can be extended to an arbitrary number of cubes.\n" +
                    "\n" +
                    "An object of any shape can be approximated as a group of cubes in contact with each other, and as the size of the cube is decreased, the precision of the approximation increases. The limiting case for infinitely small cubes is the exact equivalence.\n" +
                    "\n" +
                    "Angled surfaces do not nullify the analogy as the resultant force can be split into orthogonal components and each dealt with in the same way.\n" +
                    "\n" +
                    "Stability[edit]\n" +
                    "\n" +
                    "Illustration of the stability of bottom-heavy (left) and top-heavy (right) ships with respect to the positions of their centres of buoyancy (CB) and gravity (CG)\n" +
                    "A floating object is stable if it tends to restore itself to an equilibrium position after a small displacement. For example, floating objects will generally have vertical stability, as if the object is pushed down slightly, this will create a greater buoyancy force, which, unbalanced by the weight force, will push the object back up.\n" +
                    "\n" +
                    "Rotational stability is of great importance to floating vessels. Given a small angular displacement, the vessel may return to its original position (stable), move away from its original position (unstable), or remain where it is (neutral).\n" +
                    "\n" +
                    "Rotational stability depends on the relative lines of action of forces on an object. The upward buoyancy force on an object acts through the center of buoyancy, being the centroid of the displaced volume of fluid. The weight force on the object acts through its center of gravity. A buoyant object will be stable if the center of gravity is beneath the center of buoyancy because any angular displacement will then produce a 'righting moment'.\n" +
                    "\n" +
                    "The stability of a buoyant object at the surface is more complex, and it may remain stable even if the centre of gravity is above the centre of buoyancy, provided that when disturbed from the equilibrium position, the centre of buoyancy moves further to the same side that the centre of gravity moves, thus providing a positive righting moment. If this occurs, the floating object is said to have a positive metacentric height. This situation is typically valid for a range of heel angles, beyond which the centre of buoyancy does not move enough to provide a positive righting moment, and the object becomes unstable. It is possible to shift from positive to negative or vice versa more than once during a heeling disturbance, and many shapes are stable in more than one position.\n" +
                    "\n" +
                    "Compressible fluids and objects[edit]\n" +
                    "\n" +
                    "This section does not cite any sources. Please help improve this section by adding citations to reliable sources. Unsourced material may be challenged and removed. (January 2016) (Learn how and when to remove this template message)\n" +
                    "The atmosphere's density depends upon altitude. As an airship rises in the atmosphere, its buoyancy decreases as the density of the surrounding air decreases. In contrast, as a submarine expels water from its buoyancy tanks, it rises because its volume is constant (the volume of water it displaces if it is fully submerged) while its mass is decreased.\n" +
                    "\n" +
                    "Compressible objects[edit]\n" +
                    "As a floating object rises or falls, the forces external to it change and, as all objects are compressible to some extent or another, so does the object's volume. Buoyancy depends on volume and so an object's buoyancy reduces if it is compressed and increases if it expands.\n" +
                    "\n" +
                    "If an object at equilibrium has a compressibility less than that of the surrounding fluid, the object's equilibrium is stable and it remains at rest. If, however, its compressibility is greater, its equilibrium is then unstable, and it rises and expands on the slightest upward perturbation, or falls and compresses on the slightest downward perturbation.\n" +
                    "\n" +
                    "Submarines[edit]\n" +
                    "Submarines rise and dive by filling large tanks with seawater. To dive, the tanks are opened to allow air to exhaust out the top of the tanks, while the water flows in from the bottom. Once the weight has been balanced so the overall density of the submarine is equal to the water around it, it has neutral buoyancy and will remain at that depth. Most military submarines operate with a slightly negative buoyancy and maintain depth by using the \"lift\" of the stabilizers with forward motion.\n" +
                    "\n" +
                    "Balloons[edit]\n" +
                    "The height to which a balloon rises tends to be stable. As a balloon rises it tends to increase in volume with reducing atmospheric pressure, but the balloon itself does not expand as much as the air on which it rides. The average density of the balloon decreases less than that of the surrounding air. The weight of the displaced air is reduced. A rising balloon stops rising when it and the displaced air are equal in weight. Similarly, a sinking balloon tends to stop sinking.\n" +
                    "\n" +
                    "Divers[edit]\n" +
                    "Underwater divers are a common example of the problem of unstable buoyancy due to compressibility. The diver typically wears an exposure suit which relies on gas filled spaces for insulation, and may also wear a buoyancy compensator, which is a variable volume buoyancy bag which is inflated to increase buoyancy and deflated to decrease buoyancy. The desired condition is usually neutral buoyancy when the diver is swimming in mid-water, and this condition is unstable, so the diver is constantly making fine adjustments by control of lung volume, and has to adjust the contents of the buoyancy compensator if the depth varies.\n" +
                    "\n" +
                    "Density[edit]\n" +
                    "\n" +
                    "This section does not cite any sources. Please help improve this section by adding citations to reliable sources. Unsourced material may be challenged and removed. (January 2016) (Learn how and when to remove this template message)\n" +
                    "\n" +
                    "Density column of liquids & solids: baby oil, alcohol (with red food coloring), vegetable oil, wax, water (with blue food coloring), & aluminum.\n" +
                    "If the weight of an object is less than the weight of the displaced fluid when fully submerged, then the object has an average density that is less than the fluid and when fully submerged will experience a buoyancy force greater than its own weight. If the fluid has a surface, such as water in a lake or the sea, the object will float and settle at a level where it displaces the same weight of fluid as the weight of the object. If the object is immersed in the fluid, such as a submerged submarine or air in a balloon, it will tend to rise. If the object has exactly the same density as the fluid, then its buoyancy equals its weight. It will remain submerged in the fluid, but it will neither sink nor float, although a disturbance in either direction will cause it to drift away from its position. An object with a higher average density than the fluid will never experience more buoyancy than weight and it will sink. A ship will float even though it may be made of steel (which is much denser than water), because it encloses a volume of air (which is much less dense than water), and the resulting shape has an average density less than that of the water.\n" +
                    "\n" +
                    "See also[edit]\n" +
                    "Helmet logo for Underwater Diving portal.png Underwater diving portal\n" +
                    "Air\n" +
                    "Archimedes paradox\n" +
                    "Buoy\n" +
                    "Brunt–Väisälä frequency\n" +
                    "Buoyancy compensator (diving)\n" +
                    "Buoyancy compensator (aviation)\n" +
                    "Cartesian diver\n" +
                    "Dasymeter\n" +
                    "Diving weighting system\n" +
                    "Fluid\n" +
                    "Hydrostatics\n" +
                    "Galileo thermometer\n" +
                    "Hull (ship)\n" +
                    "Hydrometer\n" +
                    "Hydrostatic weighing\n" +
                    "Lighter than air\n" +
                    "Naval architecture\n" +
                    "Plimsoll line\n" +
                    "Pontoon\n" +
                    "Quicksand\n" +
                    "Salt fingering\n" +
                    "Submarine\n" +
                    "Swim bladder\n" +
                    "Thrust\n" +
                    "References[edit]\n" +
                    "^ Jump up to: a b Wells, John C. (2008), Longman Pronunciation Dictionary (3rd ed.), Longman, ISBN 9781405881180\n" +
                    "^ Jump up to: a b Roach, Peter (2011), Cambridge English Pronouncing Dictionary (18th ed.), Cambridge: Cambridge University Press, ISBN 9780521152532\n" +
                    "Jump up ^ Note: In the absence of surface tension, the mass of fluid displaced is equal to the submerged volume multiplied by the fluid density. High repulsive surface tension will cause the body to float higher than expected, though the same total volume will be displaced, but at a greater distance from the object. Where there is doubt about the meaning of \"volume of fluid displaced\", this should be interpreted as the overflow from a full container when the object is floated in it, or as the volume of the object below the average level of the fluid.\n" +
                    "Jump up ^ Acott, Chris (1999). \"The diving \"Law-ers\": A brief resume of their lives.\". South Pacific Underwater Medicine Society journal. 29 (1). ISSN 0813-1988. OCLC 16986801. Retrieved 2009-06-13..\n" +
                    "Jump up ^ \"Floater clustering in a standing wave: Capillarity effects drive hydrophilic or hydrophobic particles to congregate at specific points on a wave\" (PDF). 23 June 2005.\n" +
                    "External links[edit]\n" +
                    "\tLook up buoyancy in Wiktionary, the free dictionary.\n" +
                    "\tWikimedia Commons has media related to Buoyancy.\n" +
                    "Falling in Water\n" +
                    "Archimedes' Principle – background and experiment\n" +
                    "BuoyancyQuest (a website featuring buoyancy control videos)\n" +
                    "W. H. Besant (1889) Elementary Hydrostatics from Google Books.\n" +
                    "NASA's definition of buoyancy\n" +
                    "Authority control\t\n" +
                    "GND: 4137276-1\n" +
                    "Categories: ForceFluid mechanicsBuoyancy\n" +
                    "Navigation menu\n" +
                    "Not logged inTalkContributionsCreate accountLog inArticleTalkReadEditView historySearch\n" +
                    "\n" +
                    "Search Wikipedia\n" +
                    "Go\n" +
                    "Main page\n" +
                    "Contents\n" +
                    "Featured content\n" +
                    "Current events\n" +
                    "Random article\n" +
                    "Donate to Wikipedia\n" +
                    "Wikipedia store\n" +
                    "Interaction\n" +
                    "Help\n" +
                    "About Wikipedia\n" +
                    "Community portal\n" +
                    "Recent changes\n" +
                    "Contact page\n" +
                    "Tools\n" +
                    "What links here\n" +
                    "Related changes\n" +
                    "Upload file\n" +
                    "Special pages\n" +
                    "Permanent link\n" +
                    "Page information\n" +
                    "Wikidata item\n" +
                    "Cite this page\n" +
                    "Print/export\n" +
                    "Create a book\n" +
                    "Download as PDF\n" +
                    "Printable version\n" +
                    "In other projects\n" +
                    "Wikimedia Commons\n" +
                    "Languages\n" +
                    "Afrikaans\n" +
                    "العربية\n" +
                    "Bân-lâm-gú\n" +
                    "Български\n" +
                    "Bosanski\n" +
                    "Čeština\n" +
                    "ChiShona\n" +
                    "Dansk\n" +
                    "Deutsch\n" +
                    "Eesti\n" +
                    "Ελληνικά\n" +
                    "Español\n" +
                    "فارسی\n" +
                    "Français\n" +
                    "Gaeilge\n" +
                    "Galego\n" +
                    "한국어\n" +
                    "हिन्दी\n" +
                    "Hrvatski\n" +
                    "Bahasa Indonesia\n" +
                    "עברית\n" +
                    "Kreyòl ayisyen\n" +
                    "Magyar\n" +
                    "മലയാളം\n" +
                    "Bahasa Melayu\n" +
                    "日本語\n" +
                    "Norsk bokmål\n" +
                    "Norsk nynorsk\n" +
                    "Polski\n" +
                    "Português\n" +
                    "Русский\n" +
                    "Simple English\n" +
                    "Slovenščina\n" +
                    "Srpskohrvatski / српскохрватски\n" +
                    "Suomi\n" +
                    "Svenska\n" +
                    "தமிழ்\n" +
                    "ไทย\n" +
                    "Türkçe\n" +
                    "Українська\n" +
                    "中文\n" +
                    "Edit links\n");
        }

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
    }

    private void initializeSeekBars(View view) {

        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(getContext());
        int seek_bar_text_size_progress=prefs.getInt(getString(R.string.pref_seekbar_text_size_progress),0);
        int base_textSize=Integer.parseInt(getString(R.string.text_size_default));
        content.setTextSize(base_textSize+(seek_bar_text_size_progress*seek_bar_text_size_progress));
        seekbar_text_size.setProgress(seek_bar_text_size_progress);
        seekbar_text_size.setMax(10);
        seekbar_text_size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int base_text_size=Integer.parseInt(getString(R.string.text_size_default));
                content.setTextSize(base_text_size+progress*progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editValue=pref.edit();
                editValue.putInt(getString(R.string.pref_seekbar_text_size_progress),seekBar.getProgress());
                editValue.apply();
            }
        });



        int seek_bar_speed_progress=prefs.getInt(getString(R.string.pref_seekbar_speed_progress),0);
        int base_speed=Integer.parseInt(getString(R.string.speed_default));
        mScrollBy=base_speed+(seek_bar_speed_progress);
        seekbar_speed.setProgress(seek_bar_speed_progress);
        seekbar_speed.setMax(10);
        seekbar_speed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if(mPlayMode==0){
                    int base_speed=Integer.parseInt(getString(R.string.speed_default));
                    mScrollBy=base_speed+progress;
//                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editValue=pref.edit();
                editValue.putInt(getString(R.string.pref_seekbar_speed_progress),seekBar.getProgress());
                editValue.apply();
            }
        });

        int seek_bar_lineheight_progress=prefs.getInt(getString(R.string.pref_seekbar_lineheight_progress),0);
        int base_lineheight=Integer.parseInt(getString(R.string.line_height_default));
        content.setLineSpacing((float) (seek_bar_lineheight_progress*seek_bar_lineheight_progress),base_lineheight);
        float line_height= (float) (base_lineheight+(float)(0.5*seek_bar_lineheight_progress));
        seekbar_line_height.setProgress(seek_bar_lineheight_progress);
        seekbar_line_height.setMax(10);
        seekbar_line_height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                content.setLineSpacing((float) (progress*progress),1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editValue=pref.edit();
                editValue.putInt(getString(R.string.pref_seekbar_lineheight_progress),seekBar.getProgress());
                editValue.apply();
            }
        });



    }

    ScrollTimer timer=new ScrollTimer(time,15);
    @OnClick(R.id.button_play)
    public void auto_scroll(){
        if(mPlayMode==0){
            mPlayMode=1;
            timer.start();
            button_play.setImageResource(R.drawable.pause24);
//            button_play.setBackgroundColor(R.color.colorPrimaryDark);
        }else{
            mPlayMode=0;
            button_play.setImageResource(R.drawable.play24);
            timer.cancel();
        }
    }

    @OnClick(R.id.image_button_setting)
    public void onClick(){
        if(linearLayoutSeekbarContainer.getVisibility()==View.GONE){
            linearLayoutSeekbarContainer.setVisibility(View.VISIBLE);
//            imageButtonSetting.setBackgroundColor(R.color.colorPrimaryDark);
        }else if(linearLayoutSeekbarContainer.getVisibility()==View.VISIBLE){
            linearLayoutSeekbarContainer.setVisibility(View.GONE);
//            imageButtonSetting.setBackgroundColor(R.color.colorPrimary);
        }
//        linearLayoutToolBar.setVisibility(View.GONE);
    }
    public class ScrollTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public ScrollTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
        mScrollView.smoothScrollBy(0, mScrollBy);
        }

        @Override
        public void onFinish() {

        }

    }

}
