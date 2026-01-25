//1. DONE
SELECT Ph_Name AS Surgeons
FROM Physician2
WHERE Ph_Specialty = 'Surgery';
//2. DONE
SELECT DISTINCT Ph_Name || ' | ' || Ph_Specialty AS Medicines
FROM Physician2
WHERE Ph_Specialty LIKE '%Medicine%';
//3. DONE
SELECT N_Name AS "Unsupervised Nurses"
FROM Nurse2
WHERE N_Super IS NULL;
//4. DONE
SELECT N_Name
FROM Nurse2
WHERE 70000 > N_Salary 
AND N_Salary < 80000;
//5. DONE
SELECT MIN(N_Salary), MAX(N_Salary)
FROM Nurse2;
//6. DONE
SELECT AVG(N_Salary)
FROM Nurse2;
//7. DONE
SELECT MAX(N_Salary)
FROM Nurse2;
//8. DONE
SELECT N_Name
FROM Nurse2
WHERE N_Salary < ((SELECT AVG(N_Salary) FROM Nurse2)*1.2);
//9. DONE
SELECT DISTINCT N_Name AS "Nurses Monitoring"
FROM Nurse2, Bed2
WHERE B_NursID = N_ID;
//10 DONE
SELECT Ph_ID || ': ' || SUM(T_Hours) AS "Physicians Hours"
FROM Physician2
JOIN Timecard2 ON Ph_ID = T_PhysID
GROUP BY Ph_ID;
//11 DONE
SELECT DISTINCT N_Name AS "Free Nurses"
FROM Nurse2, Bed2
WHERE B_NursID != N_ID;
//12 DONE
SELECT N_Name
FROM Nurse2
WHERE N_Super = (SELECT N_ID FROM Nurse2 WHERE N_Name = 'Chris Summa');
//13
SELECT Ph_Name AS Dermatologists
FROM Physician2
JOIN Timecard2 ON Ph_ID = T_PhysID
WHERE Ph_Specialty = 'Dermatology'
GROUP BY Ph_NAme
HAVING SUM(T_Hours) > 22;
//14 DONE
SELECT Ph_Specialty, COUNT(*), SUM(T_Hours)
FROM Physician2, Timecard2
WHERE T_PhysID = Ph_ID
GROUP BY Ph_Specialty;
//15 DONE
SELECT DISTINCT n.N_Name 
FROM Nurse2 n
JOIN Nurse2 s ON n.N_Super= s.N_ID
WHERE s.N_Super = 'N01';
//16 DONE
SELECT Ph_Specialty, COUNT(*) AS Patients
FROM Physician2, Patient2
WHERE P_PhysID = Ph_ID
GROUP BY Ph_Specialty
;
//17 DONE
SELECT P_Name
FROM Patient2, Bed2, Nurse2
WHERE B_Pat = P_Number
AND B_NursID = N_ID
AND N_Salary = (SELECT MIN(N_Salary) FROM Nurse2);
//18 DONE
SELECT AVG(P_Age)
FROM Patient2, Bed2
WHERE B_Pat = P_Number
AND B_Pat IS NOT NULL;
//19 DONE
SELECT B_Number
FROM Bed2
WHERE MOD(B_Room, 2) = 1;

SELECT 'Physician: '|| COUNT(*) AS Cnt FROM Physician2;
SELECT 'Nurse: '|| COUNT(*) AS Cnt FROM Nurse2;
SELECT 'Patient: '|| COUNT(*) AS Cnt FROM Patient2;
SELECT 'Bed: '|| COUNT(*) AS Cnt FROM Bed2;
SELECT 'Timecard: '|| COUNT(*) AS Cnt FROM Timecard2;