use redou;

select sum(lastWeek.CaloriesUnderBMR) as CaloriesBurnedLastWeek
from
(
select intake.dateCreated, sum(caloriesThisMeal) as DailyCalories, deficit.totalCaloriesBurned as CaloriesBurned, (deficit.totalCaloriesBurned - sum(caloriesThisMeal))as CaloriesUnderBMR
from daily_caloric_intake as intake
inner join daily_exercise_caloric_deficit as deficit 
	on intake.user_id = deficit.user_id and intake.dateCreated = deficit.dateCreated
group by intake.dateCreated, totalCaloriesBurned
having dateCreated BETWEEN '2020/02/05' AND '2020/02/11' -- LAST 7 DAYS
order by dateCreated desc
)lastWeek
-- lost 2lbs = -7000 calories
