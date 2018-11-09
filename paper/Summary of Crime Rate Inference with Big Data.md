데이터 베이스 프로젝트를 수행하기 위해 참조 한 논문에서
파트별로 필요하다고 생각한 내용을 정리한 글이다. 

# **Crime Rate Inference with Big Data**

## 1. INTRODUCTION

**Understanding the *neighborhood context of crime is particulaly important because victimiztion and other forms of crime exposures have many severe consequences.**

- neighborhood context : 높은 의료비, violent death, 정신건강 문제, aggression, 약물 중독, post-traumatic stress disorder, 자살, 낮은 학업 성취율, 저 취업률.

**Recently, big data reflecting city dynamics have become widely available,e.g., traffic flow, human mobility, social media, and crowd-generated Points-Of-Interest (POI).**

**...**

**we propose to study two newer types of urban data: POI and taxi flow.**

- POI : information such as GPS coordinates. food, shop, transit, education.  

**Taxis may be preferred to public transpotation by offenders traveling to a crime location as they offer more pirvacy and more flexible pick-up and drop-off points.**



범죄를 neighborhood context와 연관지어 이해해 왔었다. 최근들어 빅데이터를 반영하여 GPS기반의 POI 데이터와 보다 사적인 장소에 대한 데이터가 있는 Taxi flow data를 이용하여 범죄에 대한 예측을 한다.

## 2. RELATED WORK

Overall, the existing work on crime prediction can be categorized
into three paradigms.

**1. Time-centric paradigm.**

**This line of work focuses on the temporal dimension of crime incidents.**

**This paper validates the claim that a proportion of offending is driven by the availability of opportunities presented in the routine lives of offenders.**

시간적인 관점이다. 
예전에 범죄가 발생했던 위치등을 참고한다.

**2. Place-centric paradigm**

**Most existing work adopt a placecentric**
**paradigm, where the research question is to predict the location of crime incidents. The predicted crime location is usually referred by the term hotspot, which has various geographical size.**

대부분의 연구들이 장소를 중점으로 한 관점이다.
범죄가 일어났던 장소들을 hotspot으로 지정하고 연구한다.

**3. Population-centric paradigm**



**research focuses on the criminal profiling at individual and community levels. At the individual level, [40] aim to automatically identify crimes committed by the same individual from a historical crime database.**

개인차원은 역사적으로 일어났던걸 토대로 식별

**At the community level, Buczak et al. [10] use fuzzy association rule mining to find crime patterns. The rules they found are consistent across all regions. The paper constructs association rules from population demographics in communities.**

연관 규칙을 이용해 범죄 패턴을 찾아낸다. 발견된 패턴은 모든 지역에서 일관된다.

**we use POI to enhance the demographics information and use taxi flow as hyperlinks to enhance the geographical proximity correlation. Although our problem does not consider the temporal dimension of crime in depth, it could be a promising supplement to better profile crime.**

POI로 인구통계 정보와 taxi flow를 이용해 지리적 상관성을 향상시켜 연구함.
시간 차원은 고려하지 않았다.

## **3. OVERVIEW**

**We use vector ~y = [y1, y2, . . . , yn] to denote the crime rates in regions.** 
**The crime rate inference problem is to estimate the crime rate in one region using the crime rate of other regions in the same year by considering the features of regions and correlations between regions.**

벡터에 지역 범죄율을 넣어둔다. 다른 지역의 범죄율을 참조하여 범죄율 계산에 사용하기 위해서

**In this paper we study the crime rate inference problem. More specifically, we estimate the crime rate of some regions given the information of all the other regions. Without loss of generality, we assume there is one community area t with crime rate yt missing, and we use the crime rate of all the other regions {yi}\yt to infer this missing value. Our problem is mathematically formalized as follows**

![](https://raw.githubusercontent.com/pci2676/db_pjt/master/img/1.JPG)

**where X refers to observed extra information of all those community areas.**



**Edge feature: (1) Geographical influence. Geographical influence considers the crime rate of the nearby locations. This feature has been extensively used in literature as well. To estimate the focal region, the crime rate of nearby regions are weighted according to spatial distances. (2) Hyperlink by taxi flow. Locations are connected through the frequent trips made by humans, which can be considered as the hyperlinks in space. This type of feature has never been studied in literature. We propose to use taxi trips to construct the social flow. Our hypothesis is that two regions that are more strongly connected through social flow will influence each other’s crime rate.**

특정 지역의 범죄율 추정을 위해 주변 지역의 범죄율에 가중치를 준다.
Taxi flow data를 이용, 사회적 흐름이 서로 다른 지역의 범죄율에 영향을 준다.



## 4.INFERENCE MODEL

#### Negative Binomial Regression

**In our problem, we aim to infer the crime rate, which is guaranteed to be a non-negative integer. However, linear regression does not ensure this property. Poisson regression is another form of regression, more appropriate for count data than linear regression [19],[27]. With shortened notation X, the Poisson regression model has the exponential function as link function**

![](https://raw.githubusercontent.com/pci2676/db_pjt/master/img/2.JPG)

범죄율은 음수가 아닌 정수로 나와야 하는데 일반적인 선형회귀는 그렇지 않은 회귀를 써야한다.
위는 푸아송 회귀에 관한 내용.

**This comes from the assumption that y follows Poisson distribution with mean λ. Additionally, the mean λ is determined by observed independent variables X, with the link function.**

![](https://raw.githubusercontent.com/pci2676/db_pjt/master/img/3.JPG)

 위 식은 y가 평균 λ를 갖는 푸아송 분포를 따른다는 가정으로부터 나온다. 또한, 평균 λ는 관측 된 독립 변수 X에 의해 결정되며, 링크 함수에 의해 결정된다.

**However, Poisson regression enforces the mean and variance of dependent variable y to be equal. This restriction leads to the “overdispersion” issue for some real problems, that is the presence of larger variability in data set than the statistical model expected.**

그러나 과분산 문제가 발생하여 예상하는 모델이 안나올 수 있기에 음이항회귀를 써야한다.

**This is exactly the probability density function of negative binomial distribution.**

![](https://raw.githubusercontent.com/pci2676/db_pjt/master/img/4.JPG)

**In negative binomial regression, the link function is**

![](https://raw.githubusercontent.com/pci2676/db_pjt/master/img/5.JPG)

위 식이 최종적으로 사용할 식이고 링크함수는 위와 같다.

![](https://raw.githubusercontent.com/pci2676/db_pjt/master/img/6.JPG)



## 5. FEATURE EXTRACTION

**total population, population density, poverty, disadvantage index, residential stability, ethnic diversity, race distribution.**

인구 밀도, 빈곤, 불이익 지수, 주거 안정성, 인종 다양성, 인종 분포.

**food, residence, travel, arts & entertainment, outdoors & recreation, college & education, nightlife, professional, shops, and event.**

음식, 주거, 여행, 예술 및 엔터테인먼트, 아웃도어 활동 및 레크리에이션, 대학 및 교육, 유흥, 전문, 상점 및 이벤트.

![](https://raw.githubusercontent.com/pci2676/db_pjt/master/img/7.JPG)

## 6.EXPERIMENTS

**We adopt leave-one-out evaluation to estimate the crime rate of one geographic region given all the information of all the other regions.**
**When we construct the spatial/social lag variable for the training data, the effect of testing region is completely removed.**

leave-one-out 방식은 데이터가 10개 있으면 1개를 평가군으로 두고 나머지 9개를 학습군으로 사용하는것으로 그 학습을 데이터 갯수만큼 시행하는 것
