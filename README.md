# Project Specifications and Questions
## 1. Context-Sensing Application Development
Imagine you are new to the programming world and not proficient enough in coding. But, you have a brilliant idea where you want to develop a context-sensing application like Project 1.  You come across the Heath-Dev paper and want it to build your application. Specify what Specifications you should provide to the Health-Dev framework to develop the code ideally. (15 points)

**Answer:**

### I. Sensor Specifications

#### a. Camera Sensor

- **Sensor Properties:** Camera
- **Sensor Subcomponents:**
   - Algorithm: heartRateCalculator
   - Communication protocol: CSE - camera serial interface
- **Sensor Connections:**
   - Input: start recording signal, stop recording signal
   - Output: Frames (count = frames per sec * duration of recording), pixels depending on camera specifications

#### b. Orientation Sensors

- **Sensor Properties:** Accelerometer
- **Sensor Subcomponents:**
   - Algorithm: rotation vector sensor, respiratoryRateCalculator
   - Serial connection interface
- **Sensor Connections:**
   - Input: start recording and stop recording signal
   - Output: Orientation in X, Y, Z

### II. Network Specifications
#### a. Topology:
Signal is sent from microprocessor to the sensors to start or stop. Data is sent from the sensors to the microprocessors, being stored in RAM during computation.
#### b. Energy Management: mode of operation - 
only on during transmission

### III. Smart Phone Specification
#### Version:
- minimum SDK - API 24 "Nougat", Android 7.0
#### User Interface (UI):
1.	HomeActivity
    1.	  Image
         1.	User Image
    2.	Text
         1.	User name
         2.	User Age
         3.	User Gender
    3.	Button
         1.	startVitalityActivity
         2.	startHistoryActivity
2.	VitalityActivity
    1.	Button
         1.	calculateHeartRate – start signal to camera, stop signal after 45 seconds
             1.	Data sent to microprocessor that sends it to heartRateCalculator and receives an INTEGER.
         2.	calculateRespiratoryRate – start signal to orientation sensors, stop signal after 45 seconds
             1.	data sent to microprocessor that sends data to respiratoryRateCalculator and receives an INTEGER.
         3.	Symptoms
             1.	Start SymptomsActivity, send respiratoryRate and heartrate to SymptomsActivity as part of Intent.
3.	SymptomsActivity
    1.	Card
         1.	SymptomName
         2.	Slider (0-5): INT
    2.	Submit
         1.	Saves the Symptoms + heartrate + respiratoryrate + date + time and save in realmDB.
4.	HistoryActivity
1.	Table
    1.	Date and Time
    2.	SymptomRating
#### Communication: Sensors are part of the device. Communication occurs between the microprocessor and sensor boards using mobile interface and serial interface.


## 2. User Feedback and Application Development

In Project 1 you have stored the user’s symptoms data in the local server. Using the bHealthy application suite how can you provide feedback to the user and develop a novel application to improve context sensing and use that to generate the model of the user? (15 points)

**Answer:**

BHealthy architecture consists of 5 components - 
1. Physiological sensors
2. Assessment application to determine mental and physical health of the user
3. activity suggestion module, takes assessments and recommends training applications based on the mental state
4. training applications
5. generation of wellness report

Ideally, these components should be leveraged in conjunction with the Project's data to provide well suited and personalized feedback to the user.

This could be achieved using Data analysis, feedback mechanism, context sensing improvement, personalized recommendations and continuous improvements.
This cycle is similar to SDLC.

### I. Data Analysis

1. **Collect Data:**
   - Data is collected using the project's application and stored in DB.

2. **Analyze :**
   - Leverage ML techniques to identify trends, to be used for recommendations.

### II. Feedback Mechanism

1. **Real-Time Feedback:**
   - Any urgent health alerts must be provided immediately, such as abnormal rise or fall in health or respiratory rates.

2. **Periodic Reports:**
   - Periodic reports to summarize health.

### III. Context Sensing Improvement

1. **User Behavior Modeling:**
   - Develop a user behavior model using the collected data and contextual information.
   - Use this model to predict potential health issues and provide proactive recommendations.
   - ECG and EEG sensors of the bHealthy app can be leveraged to collect data on user behavior as well.

### IV. User Interface Enhancements

1. **Interactive Dashboard:**
   - Interactive dashboard allowing users to view detailed reports and insights.

2. **Personalized Recommendations:**
   - Recommendations based on the user's data, behavior model and mood.
   - Tips for improving health and well-being.

### V. Continuous Improvement

1. **User Feedback Loop:**
   - Implement a feedback loop where users can provide feedback on the application's performance and recommendations.
   - Use this feedback to continuously improve the application.

2. **Regular Updates:**
   - Regularly update the application with new features and improvements based on user feedback and advancements in health monitoring technology.


## 3. Mobile Computing Perspectives

A common assumption is mobile computing is mostly about app development. After completing Project 1 and reading both papers, have your views changed? If yes, what do you think mobile computing is about and why? If no, please explain why you still think mobile computing is mostly about app development, providing examples to support your viewpoint  (10 points)

**Answer:**

After completing Project 1 and reading both papers, my views on mobile computing have changed. \
Initially, I used to believe mobile computing was primarily about app development.\
However, I now understand that mobile computing encompasses a broader range of activities and technologies.

### I. Integration of Sensors and Context Awareness

Mobile computing is not just about creating applications but also about integrating various sensors to gather contextual data. This data can be used to provide more personalized and context-aware services to users. For example, using accelerometers, GPS, and heart rate sensors to monitor a user's health and activity levels in real-time.

### II. Data Analysis and Machine Learning

Mobile computing involves significant data analysis and the application of machine learning algorithms to make sense of the collected data. This analysis can lead to actionable insights and predictions, enhancing the user experience. For instance, analyzing health data to predict potential health issues and provide proactive recommendations.

### III. Network and Communication Protocols

Effective mobile computing requires robust network and communication protocols to ensure seamless data transmission between devices and servers. This includes managing data synchronization, handling intermittent connectivity, and ensuring data security and privacy.

### IV. Energy Management

Mobile devices have limited battery life, making energy management a critical aspect of mobile computing. Developing energy-efficient algorithms and optimizing sensor usage are essential to prolong battery life while maintaining functionality.

### V. User Experience and Interface Design

While app development is a significant part of mobile computing, creating intuitive and user-friendly interfaces is equally important. The user experience (UX) design must consider the constraints and capabilities of mobile devices to provide a seamless and engaging experience.

### Examples

1. **Health Monitoring Applications:**
   - These applications go beyond simple app development by integrating various sensors, analyzing health data, and providing real-time feedback and recommendations.

2. **Smart Home Systems:**
   - Mobile computing in smart home systems involves controlling and monitoring home devices through mobile apps, integrating IoT devices, and ensuring secure communication between devices.

3. **Augmented Reality (AR) Applications:**
   - AR applications leverage mobile device sensors, cameras, and advanced algorithms to provide immersive experiences, demonstrating the complexity and breadth of mobile computing.

In conclusion, mobile computing is a multifaceted field that extends beyond app development to include sensor integration, data analysis, network management, energy efficiency, and user experience design. This holistic approach is essential to fully leverage the potential of mobile technologies.