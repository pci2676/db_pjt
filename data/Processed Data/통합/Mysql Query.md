```mysql
SELECT main.*,sub.유흥주점
FROM(SELECT main.*,sub.age,sub.65세인구
	FROM (SELECT *
			FROM (SELECT main.*,sub.cctv
					FROM (SELECT main.*,sub.death,sub.injured
							FROM (SELECT main.*,sub.tax
									FROM (SELECT main.*,sub.suicide_rate
											FROM (SELECT main.crimeRate,sub.city_idx,sub.total,sub.out_per,sub.total_m,sub.total_w,sub.out_m,sub.out_w
													FROM (SELECT *
															FROM crimeRate
															WHERE year=2016) as main
												INNER JOIN (SELECT *
															FROM population
															WHERE year=2016 AND age = '합계') as sub
														ON main.city_idx=sub.city_idx) as main
										INNER JOIN (SELECT city_idx,suicide_rate
													FROM suicide
													WHERE year = 2016) as sub
												ON main.city_idx=sub.city_idx) as main
								INNER JOIN (SELECT sum(tax) as tax, city_idx
											FROM tax
											WHERE year=2016
											GROUP BY city_idx) as sub
										ON main.city_idx=sub.city_idx) as main
						INNER JOIN (SELECT city_idx,SUM(death) as 'death',SUM(injured) as 'injured'
									FROM trafficAccident
									WHERE year =2016
									GROUP BY city_idx) as sub
								ON main.city_idx=sub.city_idx) as main
				INNER JOIN (SELECT city_idx, sum(count) as cctv
							FROM cctv
							GROUP BY city_idx) as sub
						ON main.city_idx=sub.city_idx) as main
		INNER JOIN (city) 
				ON main.city_idx=city_id) as main
INNER JOIN (SELECT age,total as '65세인구',city_idx
FROM population
WHERE age='65세이상' AND year = 2016) as sub
ON main.city_idx=sub.city_idx) as main
INNER JOIN (SELECT city_idx, count(city_idx) as '유흥주점', openDate
FROM pub
WHERE openDate NOT LIKE '2017%' AND openDate NOT LIKE '2018%'
GROUP BY city_idx) as sub
ON main.city_idx = sub.city_idx;
```

