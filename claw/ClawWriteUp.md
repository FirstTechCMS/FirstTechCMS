# Claw Design Process

## Objectives
The claw is a critical component of our FTC robot, as it directly enables us to score points. Since its sole function is to pick up and transport samples, we were able to design it with a singular focus on efficiency and precision. Every aspect of the claw was carefully optimised to ensure it performs this task flawlessly.

## Grabber Shape
Several initial concepts were considered for the grabber’s shape, including pairs of:
- Triangular prisms
  - This is very good for having perfect grip of the robot, however we quickly discounted this as it will only allow for a single orientation of the robot and will be far too big.
- Cones
  - This means that however the claw is angled, it can pick up the sample, however will mean that the sample is always dangling downwards which means it will be extremely difficult to precisely move the sample to where we want it to go and how we want it to go.
- 3D frustums
  - This combines the angular freedom of the cone with the fixed rotation of the triangular prism, meaning that we can hold the claw vertically or horizontally and also pick it up from any angle (the edges will snap the sample to either rotation).
After evaluating these options, the 3D frustums were chosen as the optimal design. This choice allows the sample to snap into position while enabling gripping in two orientations.

## Opening and Closing Mechanism
The following mechanisms were explored for the claw’s opening and closing functionality:
- Rack and pinion system
  - Requires 1 motor
  - Good for control of sample
  - Will take a while to open/close or will be too big (depending on gear size)
- Adjacent gears
  - Requires 1 motor
  - Decent control
  - Quick to open/close
  - May be hard to implement
- Adjacent motors
  - Requries 2 motors
  - Better control
  - Harder to code
  - Easier to implement hardware
 
The final design incorporates two adjacent gears, each connected to a motor (combining the second and third mechanism) as this configuration is the simplest to implement while remaining compact and minimally intrusive to other robot components.
Initial Sketches of the Final Claw Design
![image](https://github.com/user-attachments/assets/78aafaa0-6f7a-4b12-bdb8-79e91a6318a7)
![image](https://github.com/user-attachments/assets/fb98cec5-aedd-4c92-ac02-9fb95f08daae)

# 3D Design
We used [Onshape](https://www.onshape.com/) to make a 3D model of the initial 2D design, enabling us to visualise how the claw would fit into the rest of the robot.
![image](https://github.com/user-attachments/assets/14114985-957c-4e20-9a1f-bc218c50b09f)

The holes in the circles are long to allow minute adjustments to ensure the gear teeth mesh properly and don’t slip. We ensured ample space behind the frustums (between the curved sections) to allow the sample to exist without interference from the claw.
## First printed iteration
![pro-Xeq14lxd](https://github.com/user-attachments/assets/56ff0597-1245-4ec7-8816-fa9caf364e59)
![pro-erTJIzO6](https://github.com/user-attachments/assets/31cc526d-739b-4de6-92d9-c139b179cc69)
This iteration failed because the hole for attachment was too small, meaning we had to make it bigger

## Second printed iteration
![pro-eW5VigJK](https://github.com/user-attachments/assets/b4c12121-5a8f-4c98-93fd-a7dd18efb0a1)
This iteration was nearly perfect, however we now had the issue that the gear we were using to attach the claw to the axel was too big to fit.

## Third printed iteration
![image](https://github.com/user-attachments/assets/4c56eb0d-541e-46c4-9d86-267175efd098)
This iteration now fit all the requirements, fitting perfectly with the gear (as displayed) and being the perfect size to pick up the sample.

Now that we had a final iteration, we started building around it to connect them properly and start the area which will connect to the arm.

