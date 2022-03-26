/** Wykład z SQL na przykładach
 *  @author Patryk Czarnik
 *
 * Dla przykładowej bazy danych "HR", uruchamiane na serwerze PoatgreSQL (są także wzmianki nt Oracla).
 */
  
SELECT * FROM employees;

/* To jest komentarz blokowy
czyli taki, który może rozciągać się na wiele linii. */

-- To jest komentarz jednolinijkowy.

-- Zazwyczaj będziemy pisać komentarze przed instrukcją, którą opisujemy, np. tak:

-- To jest najprostsze zapytanie: odczytaj wszystko z tabeli countries:
SELECT * FROM countries;

--* Kwestia wielkości liter w SQL *--
-- Wersja dla PostgreSQL, ze wzmiankami na temat innych baz.

-- Jeśli chodzi o słowa kluczowe SQL, np. SELECT, to wielkość liter nie ma znaczenia:
SELECT * FROM employees;
select * from employees;
Select * fRoM employees;

-- Jeśli chodzi o nazwy tabel, kolumn itd, to sprawa jest bardziej skomplikowana...

-- Gdy używamy nazw niecytowanych, to wielkość liter w praktyce nie ma znaczenia:
SELECT * FROM employees; -- OK
SELECT * FROM Employees; -- OK
select * from EMPLOYEES; -- OK

-- Ale istnieją też nazwy "cytowane" i w takich nazwach wielkość liter ma znaczenie:
SELECT * FROM "Employees"; -- nie działa
select * from "EMPLOYEES"; -- nie działa w PostgreSQL, działa w Oracle
SELECT * FROM "employees"; -- działa w PostgreSQL, nie działa w Oracle

/* Bardziej szczegółowo.
 * PostgreSQL zamienia wszystkie nazwy pisane bez cudzysłowów na nazwy pisane małymi literami.
 * Oracle zamienia wszystkie nazwy pisane bez cudzysłowów na nazwy pisane WIELKIMI LITERAMI (i to jest zgodne ze standardem SQL).
 * Dzieje się to zarówno podczas definiowania obiektów bazodanowych (tabele i ich kolumny, widoki, sekwencje itd.),
 * jak i podczas odwoływania się do nich.
 
 * Uwaga! "Nazwa cytowana" to zupełnie coś innego niż 'napis'.
 */

-- Gdy zadajemy zapytanie np.
SELECT First_Name, last_name FROM emPloyees;
-- to Postgres wykona tak naprawdę
SELECT "first_name", "last_name" FROM "employees";

-- Gdy podczas tworzenia tabeli wpisuje nazwy bez cudzysłowów,
-- to wewnętrznie będą użyte małe litery.
CREATE TABLE TEst1(kolumna varchar2(10));
INSERT INTO tEST1 VALUES('ABC');

-- Teraz prawdziwa nazwa tej tabeli to test1
-- Jeśli do odczytu używam nazwy w cudzysłowach, to muszę użyć tej wielkości liter.
SELECT * FROM "tEst1"; -- źle
SELECT * FROM "TEST1"; -- źle
SELECT * FROM "test1"; -- OK

-- Gdy używam nazw niecytowanych, to PostgreSQL zamienia je na małe litery i jest OK
SELECT * FROM test1;
SELECT * FROM tEst1;
SELECT * FROM TEST1;
-- Morał: Konsekwentnie używając nazw niecytowanych postępujemy prawidłowo.


-- Gdybym podczas tworzenia tabeli użył nazwy "cytowanej",
-- to utworzona tabela będzie miała dokładnie taką wielkość liter, jakiej użyłem.
CREATE TABLE "TesT2"("KOLUMNA" varchar2(10));
INSERT INTO "TesT2" VALUES('XYZ');

-- Teraz jedynym sposobem poprawnego odwołania się do tej tabeli jest użycie nazwy cytowanej "TesT2"
SELECT * FROM "TesT2";

-- Bo gdy użyjemy nazwy niecytowanej, to niezależnie od wielkości liter, zostanie ona zamieniona na DUZE LITERY
SELECT * FROM test2; -- źle
SELECT * FROM tEst2; -- źle bo Postgres szuka tabeli "test2", a tabela nazywa się "TesT2"

-- Nazwy "cytowane" mogą się przydać, jeśli chcemy zachować wielkość liter, użyć nazwy zarezerwowanej jako słowo kluczowe, użyć w nazwie znaków specjalnych, w tym spacji.

CREATE TABLE "Roczne zarobki" ("wartość w złotych" number(10,2));
INSERT INTO "Roczne zarobki" VALUES(1234.56);

SELECT * FROM "Roczne zarobki";

DROP TABLE test1;
DROP TABLE "TesT2";
DROP TABLE "Roczne zarobki";


-- Ja osobiście piszę nazwy bez cudzysłowów i małymi literami.
-- A słowa kluczowe SQL piszę wielkimi.
SELECT * FROM employees;
-- Ale konwencje w świecie SQL bywają różne. "Oraclowcy" często piszą odwrotnie
select * from EMPLOYEES;



--* Polecenia modyfikacji danych (DML) - podstawy *--

-- INSERT - wstawia zupełnie nowy rekord do tabeli (takie id nie może wcześniej występować)

INSERT INTO countries(country_id, country_name, region_id)
    VALUES('PL', 'Poland', 1);

-- Gdy podajemy nazwy kolumn do wstawienia, mamy prawo podać je w zmienionej kolejności lub niektóre pominąć.
-- Dla pominiętych przyjmowana jest wartość domyślna z definicji tabeli lub NULL.
-- (Można też jawnie wpisać DEFAULT lub NULL w miejsce wartości.)
INSERT INTO countries(country_name, country_id)
    VALUES('San Escobar', 'Se');

-- Jeśli za tabelą nie podamy nazw kolumn, musimy wstawić wszystkie w tej samej kolejności jak w definicji tabeli.
INSERT INTO countries
    VALUES('CZ', 'Czech Republic', 1);


-- UPDATE - modyfikacja istniejących danych

UPDATE countries SET country_name = 'Czechia' WHERE country_id = 'CZ';

-- Przykład zmiany w wielu rekordach: programistom dajemy podwyżkę 10 tys. i ustawiamy prowizję na 10%
-- Nowa wartość może zależeć od dotychczasowej.
UPDATE employees SET salary = salary + 10000, comission_pct = 0.1 WHERE job_id = 'IT_PROG';

SELECT * FROM employees ORDER BY employee_id;

-- Cofnięcie tych zmian.
-- UPDATE employees SET salary = salary - 10000, comission_pct = NULL WHERE job_id = 'IT_PROG';

-- DELETE - usunięcie rekordów wskazanych za pomocą warunku WHERE

DELETE FROM countries WHERE country_id IN ('PL', 'CZ', 'Se');

-- Uwaga: Brak WHERE spowoduje usunięcie wszystkich rekordów. Uwaga na zaznaczanie.


--* SELECT *--
/* Ogólna składnia polecenia SELECT:
SELECT kolumny
FROM tabele
WHERE warunek rekordów
GROUP BY kryteria grupowania
HAVING warunek grup
ORDER BY kryteria sortowania
...

Poszczególne części nazywa się "kaluzulami" (clause).
Na końcu można dopisywać różne niestandardowe rozszerzenia poszczególnych baz danych,
np. LIMIT / OFFSET w PostgreSQL.
*/

-- Sama klauzula SELECT określa kolumny, które mają znaleźć się w wyniku.

SELECT first_name, last_name, salary
FROM employees;

-- Gdy wpiszemy *, to odczytamy wszystkie dostępne kolumny.
SELECT * FROM employees;

-- Teraz odczytamy wszystkie kolumny z obu tabel.
SELECT * FROM employees, departments;

-- A to odczyta trzy kolumny z employees, oraz wszystkie kolumny z departments.
SELECT employees.first_name, employees.last_name, employees.salary, departments.*
FROM employees, departments;

-- Jeśli nazwy kolumn są jednoznaczne (tzn. znajdują się tylko w jednej z podanych tabel),
-- można nie pisać prefiksu z nazwą tabeli.
SELECT first_name, last_name, salary, departments.*
FROM employees, departments;

SELECT first_name, last_name, salary, department_name
FROM employees, departments;

-- Kolumna manager_id występuje w obu tabelach i teraz nie mogę jej odczytać bez podawania nazwy tabeli
-- BŁĄD
SELECT manager_id
FROM employees, departments;

-- OK
SELECT employees.manager_id, departments.manager_id
FROM employees, departments;

-- Jeśli chcę odczytać wszystkie kolumny z tabeli, a oprócz nich dodać jeszcze jakieś (np. wyliczane wyrażeniem),
-- to w Postgresie MOŻNA użyć symbolu * , a nie zadziałałoby to w Oracle
SELECT *, lower(email) || '@alx.pl' as prawdziwy_email
FROM employees;

-- W Oracle trzeba poprzedzić * nazwą tabeli.
SELECT employees.*, lower(email) || '@alx.pl' as prawdziwy_email
FROM employees;

-- W klauzuli SELECT można nie tylko wskazywać istniejące kolumny,
-- ale można też wpisywać wyrażenia, które coś obliczają.

SELECT first_name, last_name, salary,
    12 * salary,
    length(last_name),
    upper(first_name || ' ' || last_name),
    current_timestamp,
    2+2
FROM employees;

-- Wynikowym kolumnom można nadawać własne nazwy. To są tzw. aliasy kolumn.
-- Przy okazji: ciekawy, bardzo praktyczny, sposób formatowania kodu
SELECT first_name AS imie
    , last_name AS nazwisko
    , salary
    ,12 * salary AS roczne_zarobki
    ,length(last_name) AS dlugosc_nazwiska
    ,upper(first_name || ' ' || last_name) AS kto_tam
    ,current_timestamp AS czas
    ,2+2 AS wynik
FROM employees;

-- W praktyce najczęściej alias definiuje się za pomocą "Nazwy cytowanej",
-- aby zachować wielkoćć liter i aby można było użyć spacji i innych znaków:
SELECT first_name as "Imię", last_name as "Nazwisko"
    ,salary as "Miesięczne zarobki"
    ,12 * salary as "Roczne zarobki"
FROM employees;

-- W Oracle obowiązkowe jest pisanie FROM.

-- To jest poprawne w PostgreSQL, SQLite, MySQL, ale nie w Oracle:
SELECT 2+2;
SELECT current_date, current_timestamp, random();

-- W Oracle w sytuacjach, gdy chcemy coś wykonać bez odczytywania danych z tabel,
-- korzysta się z wirtualnej tabeli DUAL:
-- To nie działa w PostgreSQL
SELECT 2+3, current_date, current_timestamp FROM dual;

-- Zobaczmy co ta tabela zawiera... :-)
-- SELECT * FROM dual;


-- DISTINCT powoduje pobranie danych bez powtórzeń:
SELECT first_name FROM employees;
SELECT DISTINCT first_name FROM employees;

-- Jeśli zapytanie zwraca kilka kolumn, to unikalne mają być "krotki", czyli kombinacje wartości.
-- Np. teraz niektóre imiona się powtarzają, niektóre nazwiska się powtarzają,
-- ale nie ma osoby o identycznej kombinacji imienia i nazwiska.
SELECT DISTINCT first_name, last_name FROM employees;


--* Klauzula WHERE *--

/* W WHERE podaje się warunek logiczny,
który dla każdego wiersza decyduje czy ten wiersz ma znaleźć się wśród wyników.
W warunku można odwoływać się do pól rekordu na podobnych zasadach jak w SELECT
(to się zmieni, gdy dojdziemy do grupowania).

Rozumując programistycznie, można sobie wyobrazić, że serwer bazodanowy czyta wszystkie rekordy z tabeli
i w pętli dla każdego rekordu sprawdza warunek.
Jednak zwn wydajność bazy danych dokonują różnych optymalizacji,
dzięki którym mogą szybciej znajdować rekordy spełniające warunek.
Zasadniczą rolę w przyspieszaniu wyszukiwania pełnią indeksy,
dzięki którym (w wielkim uproszczeniu) dla podanego kryterium baza od razu ma wskazane,
które rekordy należy odczytać - nie przegląda już pozostałych.
Indeksy spełnią swoją rolę, jeśli w warunku odwołujemy się do zindeksowanych kolumn lub wyrażeń.
*/

SELECT * FROM employees
WHERE salary > 10000;

-- Uwaga dla programistów: sprawdzenie równości to po prostu pojedyncze =
SELECT * FROM employees
WHERE salary = 10000;

-- Uwaga dla programistów Javy: także dla napisów  :-)
SELECT * FROM employees
WHERE last_name = 'King';

-- Co więcej, nie tylko lczby, ale także napisy, daty itp. można porównywać na zasadzie mniejsze/większe i sortować.
SELECT * FROM employees
WHERE last_name <= 'King';

-- Można używać porównań: < <= > >= =  
-- Nierówne na dwa sposoby: != <>
SELECT * FROM employees WHERE last_name != 'King';
SELECT * FROM employees WHERE last_name <> 'King';

-- Spójniki logiczne: AND, OR, NOT
-- Odczytaj te osoby, które zarabiają > 10 tys i jednocześnie mają na nazwisko King
-- AND - logiczne "i", koniunkcja, działa jak część wspólna zbiorów
SELECT * FROM employees WHERE salary > 10000 AND last_name = 'King';

-- OR - logiczne "lub", alternatywa, działa jak suma zbiorów
SELECT * FROM employees WHERE salary > 10000 OR last_name = 'King';

-- NOT - negacja, odwrócenie znaczenia warunku
SELECT * FROM employees WHERE NOT last_name = 'King';

-- Wypiszmy osoby, które zarabiają od 5 do 10 tys.
SELECT * FROM employees
WHERE salary >= 5000 AND salary <= 10000;

-- Akurat jeśli chodzi o sprawdzanie przedziałów, można użyć operatora BETWEEN:
-- Zauważmy, ze BETWEEN opisuje przedziały obustronnie domknięte.
SELECT * FROM employees
WHERE salary BETWEEN 5000 AND 10000;

-- Wypisz pracowników o nazwiskach na litery od C do K
SELECT * FROM employees WHERE last_name BETWEEN 'C' AND 'L';


-- Odczytajmy pracowników ze stanowisk ST_CLERK oraz ST_MAN
SELECT * FROM employees
WHERE job_id = 'ST_CLERK' OR job_id = 'ST_MAN';

-- Operator IN sprawdza czy wartość należy do zbioru.
-- Zbiór można podać w treści zapytania wymieniając wartości w nawiasach okrągłych:
SELECT * FROM employees
WHERE job_id IN ('ST_CLERK', 'ST_MAN');

-- Ale operator IN może być też używany w połączeniu z podzapytaniem...
-- Zakładając, że posiadamy tabelę selected_jobs, można by tak:
/* SELECT * FROM employees
   WHERE job_id IN (SELECT job FROM selected_jobs); */

-- Operator LIKE - sprawdzanie czy napis pasuje do wzorca
SELECT * FROM employees
WHERE job_id LIKE 'ST%';

-- We wzorcach:
-- znak % oznacza dowolnej długości ciąg dowolnych znaków
-- znak _ oznacza pojedynczy dowolny znak
-- za pomocą symbolu \ można "wy-escape-ować" znaki specjalne, gdyby miały być faktyczną treścią
SELECT * FROM employees WHERE job_id LIKE 'ST\_%';


-- Wielkość liter MA znaczenie.

-- wszystkie nazwiska zaczynające się na K
SELECT first_name, last_name FROM employees WHERE last_name LIKE 'K%';

-- wszystkie nazwiska zaczynające się na K , a kończące się na g
SELECT first_name, last_name FROM employees WHERE last_name LIKE 'K%g';

-- druga litera nazwiska jest równa a
SELECT * FROM employees WHERE last_name LIKE '_a%';

--! Specyfika PostgreSQL !--
-- W PostgreSQL istnieje dodatkowo ILIKE, który ignoruje wielkość liter
-- bez Oxfrord
SELECT city FROM locations WHERE city LIKE '%o%o%';

-- zawiera Oxford
SELECT city FROM locations WHERE city ILIKE '%o%o%';

-- wersja Oracle:
SELECT city FROM locations WHERE lower(city) LIKE '%o%o%';

-- Informacja nt SIMILAR TO oraz regexp_match
-- https://www.postgresql.org/docs/12/functions-matching.html



-- Uwaga! W WHERE nie można używać aliasów kolumn wprowadzonych w klauzuli SELECT:
-- Takie rzeczy mogą działać w niektórych innych bazach danych
-- (tych lajtowo podchodzących do standardów, np. SQLite :-) ), ale nie w PostgreSQL ani Oracle
-- BŁĄD!
SELECT first_name, last_name, 12*salary AS roczne
FROM employees
WHERE roczne <= 100000;

-- W Postgres (i Oracle) takie rzeczy - jeśli trzeba- realizuje się zapytaniami zagnieżdżonymi:
-- OK
SELECT * FROM (
	SELECT first_name, last_name, 12*salary AS roczne
	FROM employees) sub
WHERE roczne <= 100000;


--* Temat NULL *--

-- Zobaczmy, że NULL nie jest zwykłą wartością, tylko powoduje dziwne zachowania, gdy się pojawia.

-- W tabeli employees istnieje kolumna commission_pct, która u niektórych pracowników zawiera liczbę,
-- a u pozostałych ma wartość NULL.
SELECT * FROM employees;

-- Gdy próbujemy odczytać pracowników, którzy mają prowizję równą NULL, to nie działa taki zapis,
-- dostaniemy puste wyniki:
SELECT * FROM employees WHERE commission_pct = NULL;

-- Odwrotny warunek też daje puste wyniki:
SELECT * FROM employees WHERE commission_pct != NULL;
SELECT * FROM employees WHERE NOT (commission_pct = NULL);

-- Dla wartości NULL nieprawdą jest ani > , ani <
-- Ci, którzy nie uzyskują prowizji, nie pojawią się ani w jednej, ani w drugiej grupie:
SELECT * FROM employees WHERE commission_pct > 0.2;
SELECT * FROM employees WHERE commission_pct <= 0.2;

-- Aby sprawdzić czy jest jest równa NULL, czy nie, należy użyć wyspecjalizowanych operatorów IS (NOT) NULL
SELECT * FROM employees WHERE commission_pct IS NULL;
SELECT * FROM employees WHERE commission_pct IS NOT NULL;

/* Praprzyczyną tego dziwnego zachowania jest fakt, iż na początku historii SQL uznano,
że NULL będzie oznaczał BRAK WIEDZY jaka jest wartość danego pola.
Czyli tak jakby w tym przykładzie wartość NULL znaczyła "nie wiadomo jaką prowizję uzyskuje pracownik".
Do takiego znaczenia wartości NULL dostosowano całą logikę i działanie operatorów.

W praktyce jednak często przyjmuje się, że NULL oznacza "pustą wartość".
Oznacza "wiemy, że czegoś nie ma", jest puste, zerowe, brak.
Tak jak w tym przykładzie, intencją autora tabeli employees było na pewno,
aby NULL w polu commission_pct znaczył "pracownik nie otrzymuje żadnej prowizji".
I tak jest zazwyczaj w praktyce.
Jednak logika SQL tak tego nie rozumie. Dla niej NULL oznacza "nie wiadomo".

Więcej na temat teorii można szukać pod hasłem "logika trójwartościowa SQL".
*/

-- Porównanie z NULLem daje wynik NULL
SELECT 1 = NULL;

-- Ale spójniki logiczne starają się robić swoje nawet jeśli czasami jest nieznana wartość.
SELECT 1 = 1 AND 2 = NULL;

SELECT 1 = 1 OR 2 = NULL;

-- Operacje arytmetyczne itp. , gdzie wewnątrz pojawia się NULL, dają wynik NULL.
SELECT 2 * (3 + NULL) - 15 * 4;

/* Gdy wykonujemy obliczenia, w których pojawia się wartość NULL,
   to zazwyczaj wynikiem całego wyrażenia też jest NULL („null zaraża”).

   Tutaj pewne uproszczenie: przyjmujemy, że prowizja jest liczona względem pensji,
   a nie sprzedaży (której w tej bazie danych nie ma).

   Dla każdego pracownika chcemy obliczyć jego wypłatę wraz z dodaną prowizją.
   Przy poniższym zapisie wynikiem w ostatniej kolumnie
   jest NULL u tych pracowników, którzy nie uzyskują prowizji.
*/
SELECT first_name, last_name, salary, commission_pct
    ,commission_pct * salary -- prowizja kwotowo
    ,salary + commission_pct * salary -- kwota do wypłaty
FROM employees;

-- Aby zastąpić wartość NULL inną "konkretną" wartością, można użyć funkcji COALESCE
SELECT first_name, last_name, salary, commission_pct
    ,coalesce(commission_pct * salary, 0) as "kwota prowizji"
    ,salary + coalesce(commission_pct, 0) * salary as "wypłata z prowizją"
FROM employees;

-- coalesce(wartosc1, wartosc2, ....) -- dowolnie wiele wartości po przecinku
-- zwraca pierwszą z wartości podanych jako parametry, która nie jest nullem (no chyba ze wszystkie są)

SELECT coalesce(null, null, 'bingo', null, 'kapusta') FROM dual;

-- coalesce jest częścią standardu SQL i działa w większości baz danych
-- W praktyce najczęściej zapisuje się tak: coalesce(kolumna_ktora_moze_byc_null, wartosc_domyslna)

-- Nie ma obowiązku podstawiania zera.
SELECT first_name, last_name, salary, commission_pct,
	coalesce(commission_pct, 1234)
FROM employees;


--! Specyfika Oracle !--

-- W Oraclu jest też funkcja nvl, która działa tak jak dwuargumentowy coalesce:
-- NVL(x, y):   jeśli x nie jest nullem, to zwraca x, a jeśli x jest nullem, to zwraca y
SELECT first_name, last_name, salary, commission_pct
    ,nvl(commission_pct * salary, 0) as "kwota prowizji"
    ,salary + nvl(commission_pct, 0) * salary as "wypłata z prowizją"
FROM employees;

-- Inne funkcje Oracla związane z obsługą NULL:

-- NVL2(x, y, z)
-- jeśli x nie jest NULL, to zwraca y
-- jeśli x jest NULLem, to zwraca z

SELECT first_name, last_name, commission_pct,
    nvl2(commission_pct, 'jest prowizja', 'brak prowizji')
FROM employees;

-- Na siłę można to wykorzystać do wyliczenia łącznej wypłaty:
SELECT first_name, last_name, salary, commission_pct,
    nvl2(commission_pct, salary*(1+commission_pct), salary) AS "wypłata"
FROM employees;

-- NULLIF(x, y) -- o!, to działa też w PostgreSQL
-- jeśli x = y, to wynikiem jest NULL
-- jeśli x != y, to wynikiem jest x
SELECT city, nullif(city, 'Toronto') FROM locations;


--* ORDER BY - sortowanie wyników *--

-- Zasadniczo podaje się kryterium, wg którego dane wynikowe zostaną posortowane.
SELECT * FROM employees
ORDER BY salary;

-- Domyślnie sortowanie jest rosnące.
-- Aby sortować malejąco, należy dopisać na końcu DESC
SELECT * FROM employees
ORDER BY salary DESC;

-- Można też dopisać ASC dla rosnącego, ale nie trzeba, bo tak jest domyślnie.
SELECT * FROM employees
ORDER BY salary ASC;

-- Dodatkowe opcje pozwalają określić miejsce wartości NULL.
-- Domyślnie NULL jest traktowany jak większy od wszystkich wartości
-- (tak jest w PostgreSQL i Oracle, zgodnie ze standardem; w MySQL i SQLite jest odwrotnie)
-- Przy sortowaniu rosnącym nulle będą na końcu.
SELECT * FROM employees ORDER BY commission_pct;
SELECT * FROM employees ORDER BY commission_pct ASC;

-- Przy malejącym nulle będą na początku.
SELECT * FROM employees ORDER BY commission_pct DESC;

-- Za pomocą NULLS FIRST / NULLS LAST możemy jawnie podać gdzie mają być nulle
SELECT * FROM employees ORDER BY commission_pct NULLS FIRST;
SELECT * FROM employees ORDER BY commission_pct ASC NULLS FIRST;
SELECT * FROM employees ORDER BY commission_pct DESC NULLS FIRST;
SELECT * FROM employees ORDER BY commission_pct DESC NULLS LAST;

-- Sortować można wg wielu kryteriów wymienionych po przecinku.
-- Np. sortujemy wg nazwiska, a jeśli nazwiska są jednakowe, to wg imienia:
SELECT * FROM employees
ORDER BY last_name, first_name;

-- Wtedy ASC i DESC dotyczą tylko ostatniego kryterium, przy którym są napisane.
-- Inaczej mówiąc ASC i DESC muszą być podane niezależnie dla każdego kryterium.

-- Przykład: rosnąco wg job_id, w obrębie każdej takiej grupy malejąco wg pensji,
-- a na końcu rosnąco wg nazwiska i imienia
SELECT * FROM employees
ORDER BY job_id, salary DESC, last_name, first_name;

-- Dokładnie taki sam efekt:
SELECT * FROM employees
ORDER BY job_id ASC, salary DESC, last_name ASC, first_name ASC;

-- Co można wpisać jako kryterium?
-- nazwę kolumny
SELECT * FROM employees
ORDER BY job_id, salary DESC;

-- dowolne wyrażenie, nawet takie, którego wyniku nigdzie nie wypisuję
SELECT * FROM employees
ORDER BY length(first_name || last_name);

-- przykład: wymieszaj dane losowo:
SELECT * FROM employees ORDER BY random();
-- Wersja Oracle:
-- SELECT * FROM employees ORDER BY dbms_random.random();

-- Posortuj wg pensji z dodaną prowizją, tak jak liczyliśmy wcześniej
SELECT first_name, last_name,
    salary + coalesce(commission_pct, 0) * 10000 AS "do wypłaty"
FROM employees
ORDER BY salary + coalesce(commission_pct, 0) * 10000;

-- Można też podać alias kolumny
SELECT first_name, last_name,
    salary + coalesce(commission_pct, 0) * 10000 AS "do wypłaty"
FROM employees
ORDER BY "do wypłaty";

-- Lub numer kolumny licząc od 1
SELECT first_name, last_name,
    salary + coalesce(commission_pct, 0) * 10000 AS "do wypłaty"
FROM employees
ORDER BY 3 DESC;

SELECT employee_id, first_name, last_name, job_id, salary
FROM employees
ORDER BY 4, 5 DESC, 3, 2;

-- LIMIT / OFFSET
-- W różnych bazach danych istnieją różne sposoby, aby zapytanie zwróciło tylko pierwszy rekord / X pierwszych rekordów.
-- W PostgreSQL służy do tego dodatkowa klauzuka LIMIT / OFFSET dopsywana na końcu zapytania (za klauzulą ORDER BY).

-- To zwraca pierwsze 10 rekordów
SELECT * FROM employees
ORDER BY salary DESC
LIMIT 10;

-- Używając OFFSET możemy pobierać następne grupy, np. "drugą dziesiątkę"
-- OFFSET pomija określoną liczbę rekordów i zaczyna zwracać pewną liczbę następnych
SELECT * FROM employees
ORDER BY salary DESC
LIMIT 10 OFFSET 10;

-- Od rekordu 91 do samego końca:
SELECT * FROM employees
ORDER BY salary DESC
OFFSET 90;

-- Używanie LIMIT / OFFSET bez sortowania jest poprawne technicznie,
-- ale ma mały sens logiczny.
-- Chyba, że w technicznym celu pobrania "próbki danych"...
SELECT * FROM employees
LIMIT 10;


--* Funkcje agregujące *--

SELECT * FROM employees;

-- W SQL istnieją funkcje "zwykłe" oraz "agregujące".
-- Zwykła funkcja dla każdego rekordu wejściowego zwraca osobny wynik:
SELECT round(salary) FROM employees;
SELECT sqrt(salary) FROM employees;

-- Funkcja agregująca zbiera ("kompresuje") wiele rekordów wejściowych i zwraca jeden łączny wynik:
SELECT avg(salary) FROM employees;

-- O tym, że z wielu rekordów robi się jeden wynik, decyduje sam fakt, że użyliśmy funkcji, która jest "agregująca".
-- Nie widać tego w żaden sposób w strukturze zapytania: porównajmy zapisy z sqrt i avg - są identyczne.

-- Istnieje 5 klasycznych ("kanonicznych") funkcji agregujacych SQL:
SELECT count(salary), sum(salary), avg(salary), min(salary), max(salary)
FROM employees;

-- count można używać na kilka sposobów:
-- count(*) - ilość rekordów, które są agregowane
-- count(kolumna) , ogólnie count(wyrażenie) - ilość nie-NULL-owych wartości danej kolumny/wyrażenia
-- count(DISTINCT wartość) - ilość różnych nie-NULL-owych wartości
SELECT count(*), count(department_id), count(DISTINCT department_id)
FROM employees;

-- Brakującym rekordem jest Kimberely Grant, której department_id jest równy NULL
SELECT * FROM employees WHERE department_id IS NULL;

-- Dodatkowe funkcje agregujące dostęþne w PostgreSQL
-- https://www.postgresql.org/docs/14/functions-aggregate.html
-- Kilka przykładów
SELECT avg(salary), stddev(salary), variance(salary) FROM employees;

SELECT every(length(street_address) > length(city)) FROM locations;

SELECT string_agg(city, '; ') FROM locations;

SELECT json_agg(city) FROM locations;

-- Łącząc funkcje agregujace z warunkiem WHERE możemy obliczyć statystyki tylko dla wybranych rekordów.
-- Ilu jest programistów i jaka jest ich średnia pensja?
SELECT count(*) AS ilu, avg(salary) AS srednia
FROM employees
WHERE job_id = 'IT_PROG';

-- Zauważmy, że gdy stosujemy funkcje agregujące, nie możemy już odwoływać się do wartości pojedynczych rekordów.
-- SELECT count(*) AS ilu, avg(salary) AS srednia, job_id
-- FROM employees
-- WHERE job_id = 'IT_PROG';

-- Np. takie zapytanie jest niepoprawne w SQL
-- i nie działa w PostgreSQL, Oracle, ale działa w SQLite
-- SELECT min(salary), first_name, last_name FROM employees;

--* Klauzule GROUP BY i HAVING *--

-- Powyżej za pomocą WHERE obliczyliśmy ilość i średnią dla rekordów należących do jednej grupy;
-- wszystkich, których pole job_id miało wartość 'IT_PROG'.
-- A gdybyśmy chcieli w jednym zapytaniu obliczyć takie dane dla wszystkich możliwych grup (różnych wartości job_id)?

-- Gdy chcemy obliczyć funkcje agregujące dzieląc dane na kategorie (grupy), to stosujemy właśnie klauzulę GROUP BY.

-- Porównajmy...
-- Wszystkie pensje osobno - 107 wyników
SELECT salary FROM employees;

-- Jedna liczba - średnia wszystkich - 1 wynik
SELECT avg(salary) FROM employees;

-- Dla każdej grupy osobno liczona średnia - 19 wyników, bo istnieje 19 różnych wartości job_id
SELECT avg(salary) FROM employees GROUP BY job_id;

-- W praktyce wypisujemy także kolumnę, ze względu na którą grupowaliśmy, aby wyniki były czytelne:
SELECT job_id, count(*) AS ilu, avg(salary) AS srednia
FROM employees
GROUP BY job_id;

-- W PostgreSQL, inaczej niż w Oracle, a podobnie jak np. w MySQl, w GROUP BY można użyć aliasu kolumny
SELECT substring(last_name, 1, 1) AS litera, count(*) AS ilosc
FROM employees
GROUP BY litera
ORDER BY litera;

-- W Oraclu trzeba tak:
SELECT substring(last_name, 1, 1) AS litera, count(*) AS ilosc
FROM employees
GROUP BY substring(last_name, 1, 1)
ORDER BY litera;

-- Jeśli chcemy przefiltrować całe grupy, to warunek ich dotyczący wpisujemy w klauzulę HAVING.
-- Przykład: wypisz stanowiska, na których pracuje co najmniej 10 osób:
SELECT job_id, count(*) AS ilu, avg(salary) AS srednia
FROM employees
GROUP BY job_id
HAVING count(*) >= 10;

-- WHERE - warunki dot. pojedynczych rekordów stosowane przed grupowaniem
-- HAVING - warunki dotyczące całych rekordów, po grupowaniu

-- Bierzemy pracowników, którzy zarabiają < 6 tys, grupujemy wg stanowisk
-- i następnie wyliczamy średnią tylko dla tych pracowników.
-- Zauważmy, że:
-- 1) wypisuje się stanowisko ST_MAN, bo jeden z ST_MANów zarabia < 6 tys.
-- 2) dla stanowiska IT_PROG średnia wynosi 4600, bo uwzględniamy tylko tych 3 programistów, którzy zarabiają < 6tys.
SELECT job_id, count(*) AS ilu, avg(salary) AS srednia, min(salary) AS min, max(salary) AS max
FROM employees
WHERE salary < 6000
GROUP BY job_id;

-- Wszystkich pracowników grupujemy wg stanowisk i wyliczamy średnią dla całych grup.
-- A następnie wyświetlamy tylko te grupy (te stanowiska), na których średnia pensja > 6 tys.
-- Zauważmy, ze:
-- 1) NIE wypisuje się stanowisko ST_MAN, bo średnia ST_MANów wynosi 7280 i nie przechodzi przez HAVING
-- 2) dla stanowiska IT_PROG średnia wynosi 5760, bo to średnia wszystkich programistów
SELECT job_id, count(*) AS ilu, avg(salary) AS srednia, min(salary) AS min, max(salary) AS max
FROM employees
GROUP BY job_id
HAVING avg(salary) < 6000;

-- Wypisz te stanowiska, na których co najmniej 5 osób zarabia co najmniej 5 tys.
-- Zauważmy, ze nie ma tu programistów, bo tylko 3 zarabia >= 5tys.
SELECT job_id, count(*) AS ilu, avg(salary) AS srednia, min(salary) AS min, max(salary) AS max
FROM employees
WHERE salary >= 5000
GROUP BY job_id
HAVING count(*) >= 5;

-- Przy okazji: Zamiast HAVING można też umieści zapytanie z GROUP BY jako podzapytanie
-- w zewnętrznym zapytaniu, a w tym zewnętrznym użyć WHERE.
SELECT * FROM (
	SELECT job_id, count(*) AS ilu, avg(salary) AS srednia, min(salary) AS min, max(salary) AS max
	FROM employees
	GROUP BY job_id) podzapytanie
WHERE srednia < 6000;


-- Jeśli stosujemy grupowanie, to w klauzulach SELECT oraz HAVING nie możemy odwoływać się bezpośrednio do tych kolumn,
-- które nie są wymienione w GROUP BY.
-- Dla pozostałych musimy użyć funkcje agregującej.

-- O ile takie zapytanie nie ma żadnego sensu:
-- źle
SELECT first_name, last_name, job_id, avg(salary)
FROM employees
GROUP BY job_id;

-- To takie zapytania (z użyciem min i max) mają sens i zadziałają np. w SQLite,
-- ale PostgreSQL (jak i Oracle) dla zasady zabrania odczytywania pojedynczych wartości, jeśli stosujemy grupowanie i agregacje.
-- źle
SELECT first_name, last_name, job_id, min(salary)
FROM employees
GROUP BY job_id;

SELECT first_name, last_name, job_id, max(salary)
FROM employees
GROUP BY job_id;

-- Dalsze rozważania: po czym grupować?
-- Grupowanie po danych tekstowych, które w tabeli nie są zindeksowane jest mało wydajne.
SELECT department_name, count(employee_id) AS ilu, round(avg(salary),2) AS srednia
FROM employees FULL JOIN departments USING(department_id)
GROUP BY department_name;


-- Bardziej wydajne będzie grupowanie po ID, które mamy zindeksowane
-- tutaj można dopisać grupowanie po department_id, skoro department_name wynika bezpośrednio z department_id
-- ale nie zawsze można pogrupować po samym id
-- SELECT department_name, count(employee_id) AS ilu, round(avg(salary),2) AS srednia
-- FROM employees FULL JOIN departments USING(department_id)
-- GROUP BY department_id;

-- grupowanie po id będzie pierwszym krokiem, a dla formalności musimy dopisać grupowanie po name
SELECT department_name, count(employee_id) AS ilu, round(avg(salary),2) AS srednia
FROM employees FULL JOIN departments USING(department_id)
GROUP BY department_id, department_name;

-- czy można pogrupować po samym ID, jeśli z niego wynika inna wartość (np. name)?
-- W Oraclu po prostu nie można
-- w Postgresie w zależności od struktury zapytania czasami można pogrupować po samym id:
SELECT department_name, count(employee_id) AS ilu, round(avg(salary),2) AS srednia
FROM departments LEFT JOIN employees USING(department_id)
GROUP BY department_id;
-- Działa dla INNER JOIN oraz LEFT JOIN gdy tabela jest po lewej, dla RIGHT JOIN gdy tabela po prawej, nie działa dla FULL JOIN

---- Dodatkowe możliwości GROUP BY ----
-- (temat zaawansowany; jesli wydaje się trudny, to przeskocz i przejdź do JOINów)

-- wprowadzenie: można grupować wg kilku kryteriów, ale wtedy podsumowania są robione dla wszystkich kombinacji wartości
SELECT department_id, job_id, count(*), avg(salary)
FROM employees
GROUP BY department_id, job_id
ORDER BY 1, 2;

-- W Oracle istnieją specjalne dodatki do GROUP BY do tworzenia częściowych podsumowań
-- W PostgreSQL też ;-)
-- Najczęściej używa się ROLLUP. Pozostałe: CUBE i GROUPING_SETS
SELECT department_id, job_id, count(*), avg(salary)
FROM employees
GROUP BY ROLLUP(department_id, job_id)
ORDER BY 1, 2;

-- Jeśli grupujemy po jednym kryterium, to ROLLUP i CUBE dopisują jeden wiersz podsumowujący wszystkie dane (tak jakbyśmy policzyli funkcje agregujące bez wcześniejszego grupowania).
-- W miejsce kolumn, po których grupowaliśmy, w wierszu podsumowania wstawiany jest NULL, a funkcja grouping(kolumna) zwraca 1.
SELECT job_id, count(*), avg(salary), grouping(job_id)
FROM employees
GROUP BY ROLLUP(job_id)
ORDER BY 1;

SELECT department_id
	, grouping(department_id) AS grouping_department_id
	, job_id
	, grouping(job_id) AS grouping_job_id
	, count(*)
	, avg(salary)
FROM employees
GROUP BY ROLLUP(department_id, job_id)
ORDER BY 1;

SELECT CASE grouping(job_id)
  WHEN 1 THEN 'Podsumowanie:'
  ELSE job_id
  END AS "Stanowisko", count(*) AS "Ilość", round(avg(salary), 2) AS "Średnia"
FROM employees
GROUP BY ROLLUP(job_id);

-- Przy okazji: to jest przykład, gdzie trzeba użyć to_char, aby z liczby zrobić tekst,
-- bo inaczej typy nie beda sie zgadzac, nie może być czasami tekstu, czasami liczby w jednej kolumnie wynikowej.
SELECT CASE grouping(department_id)
  WHEN 1 THEN 'Podsumowanie:'
  ELSE department_id::text  -- w Oracle to_char(department_id)
  END AS "Nr dep.", count(*) AS "Ilość", round(avg(salary), 2) AS "Średnia"
FROM employees
GROUP BY ROLLUP(department_id);

-- Jeśli grupujemy po kilku kryteriach, to ROLLUP tworzy podsumowania na kolejnych poziomach, ale tylko w jednym wymiarze
SELECT department_id, job_id, count(*), avg(salary)
FROM employees
GROUP BY ROLLUP(department_id, job_id);

SELECT department_name, job_id, count(*), avg(salary), GROUPING(department_name), GROUPING(job_id)
FROM employees LEFT JOIN departments USING(department_id)
GROUP BY ROLLUP(department_name, job_id)
ORDER BY 1, 2;

-- CUBE dodaje podsumowania dla wszystkich wymiarów / kombinacji
-- W tym konkretnym przypadku CUBE (w stosunku do ROLLUP) dodaje wiersze, w których grupujemy wg job_id nie zwracając uwagi na depratment_id
SELECT department_id, job_id, count(*), avg(salary)
FROM employees
GROUP BY CUBE(department_id, job_id);

SELECT department_id, job_id, count(*), avg(salary), GROUPING(department_id), GROUPING(job_id)
FROM employees
GROUP BY CUBE(department_id, job_id);

-- w GROUPING SETS sami określamy, które podsumowania nas interesują

-- Grupujemy wg dep i job (standard), a dodatkowo wiersz podsumowujący wszystko)
SELECT department_id, job_id, count(*), avg(salary)
FROM employees
GROUP BY GROUPING SETS((department_id, job_id), ());

-- ROLLUP jest rownowazny takiemu GROUPING SETS:
SELECT department_id, job_id, count(*), avg(salary)
FROM employees
GROUP BY GROUPING SETS((department_id, job_id), (department_id), ());

-- CUBE jest rownowazny takiemu GROUPING SETS:
SELECT department_id, job_id, count(*), avg(salary)
FROM employees
GROUP BY GROUPING SETS((department_id, job_id), (department_id), (job_id), ());


-- Grupujemy wg dep i job (standard), a dodatkowo podsumowania wg dep; NIE MA TUTAJ podsumowania wszystkiego)
SELECT department_id, job_id, count(*), avg(salary)
FROM employees
GROUP BY GROUPING SETS((department_id, job_id), (department_id));


-- Zapytania do wielu tabel i JOIN --
-- Zajrzymy do kilku tabel - są tam "wspólne kolumny"
SELECT * FROM employees;
SELECT * FROM jobs;
SELECT * FROM departments;
SELECT * FROM locations;

-- Dane są rozdzielone do osobnych tabel, bo taka jest dobra praktyka relacyjnych baz danych.
-- "Dążymy do postaci normalnej".

-- Ale podczas zadawania zapytań często chcemy połączyć rekordy z jednej tabeli
-- z odpowiadającymi im rekordami z innej tabeli.

-- Można we FROM podać więcej niż jedną tabelę:
SELECT *
FROM employees, jobs;
-- W takiej sytuacji baza danych zwraca wszystkie możliwe kombinacje rekordów z jednej tabeli z rekordami z drugiej tabeli
-- To jest tzw. "iloczyn kartezjański".

-- Liczba rekordów jest iloczynem liczby rekordów w jednej i drugiej tabeli
SELECT count(*) FROM employees, jobs;
SELECT count(*) FROM employees;
SELECT count(*) FROM jobs;
SELECT 107 * 19;

-- Aby zobaczyć tylko prawidłowo dopasowane rekordy, można dodać warunek w WHERE:
SELECT *
FROM employees, jobs
WHERE employees.job_id = jobs.job_id;

-- Można też użyć notacji "JOIN", ma one wiele możliwości, ale np. tak:
SELECT *
FROM employees JOIN jobs ON employees.job_id = jobs.job_id;

-- Aliasy tabel
-- Zamiast powtarzać długie nazwy tabel:
SELECT *
FROM employees, departments, locations
WHERE departments.department_id = employees.department_id
  AND locations.location_id = departments.location_id;

-- ... można wprowadzić "aliasy tabel" w sekcji FROM:
SELECT *
FROM employees emp, departments d, locations l
WHERE d.department_id = emp.department_id
  AND l.location_id = d.location_id;

-- Alias to nasza lokalna nazwa, którą nadajemy tabeli w obrębie jednego zapytania.
-- Alias wprowadzamy we FROM, a używamy w warunkach i pozostałych klauzulach zamiast nazwy tabeli
-- (nie możemy już używać oryginalnej nazwy tabeli!).

-- Są sytuacje, gdy alias tabeli jest niezbędny, aby napisać jakieś zapytanie (np. tzw. self join).

-- Uwaga składniowa: w Oraclu nie wolno pisać AS przed aliasem tabeli (w PostgreSQL i MySQL można, ale lepiej pisać tak, aby było przenośnie...).

-- Składniowo konstrukcja JOIN jest częścią klauzuli FROM, a warunek w ON jest dopełnieniem JOIN.
-- Porównajmy zapis z przecinkiem i WHERE do zapisu z JOIN-em, gdy czytamy dane z 3 tabel i jeszcze mamy warunek filtrowania:

-- 1) Warunki dotyczące złączenia oraz zwykłe warunki logiczne są razem w WHERE
SELECT *
FROM employees e, departments d, locations l
WHERE d.department_id = e.department_id
    AND l.location_id = d.location_id
    AND e.salary >= 10000;

-- 2) Po kolei dodajemy tabel i dla każdej od razu po JOIN wpisujemy warunek złączenia,
--    natomiast w WHERE mamy tylko inne warunki logiczne
SELECT *
FROM employees e
    JOIN departments d ON d.department_id = e.department_id
    JOIN locations l ON l.location_id = d.location_id
WHERE e.salary >= 10000;

-- Możliwości JOIN.
-- Są trzy sposoby podawania warunku złączenia: JOIN ON, JOIN USING, NATURAL JOIN
-- Są też różne "kierunki" złączeń: INNER, LEFT, RIGHT, FULL oraz CROSS


--* Warunki złączenia *--
-- (0) warunek podany w WHERE ? wrócimy do tego na koniec

-- (1) tabela1 JOIN tabela2 ON warunek
-- Najbardziej ogólny sposób dobierania rekordów, można podać dowolny warunek logiczny.
SELECT *
FROM employees JOIN jobs ON jobs.job_id = employees.job_id;

SELECT *
FROM employees e JOIN jobs j ON j.job_id = e.job_id;

-- Ten sposób jest jedynym dostępnym, gdy kolumny mają różne nazwy w jednej i drugiej tabeli.
-- SELECT * FROM samochody_sluzbowe s JOIN pracownicy p ON s.uzytkownik = p.nr_pracownika;

-- (2) tabela1 JOIN tabela2 USING(kolumny)
-- Wybrane będą te kombinacje rekordów, w których kolumny o podanej nazwie mają jednakową wartość w obu tabelach.
-- Wymaga to istnienia kolumn(y) o identycznej nazwie.
SELECT *
FROM employees JOIN jobs USING(job_id);

-- (3) tabela1 NATURAL JOIN tabela2
-- Dopasowywane są wszystkie te kolumny, które występują pod jednakową nazwą w jednej i drugiej tabeli.
SELECT *
FROM employees NATURAL JOIN jobs;

-- Jest to dość ryzykowne, grozi nam przypadkowa zbieżność nazw.
-- W tabelach employees i departments mamy 2 kolumny o takiej samej nazwie: department_id i manager_id
-- emp.manager_id - szef pracownika, dep.manager_id - kierownik departamentu
-- To zapytanie zwraca tylko tych pracowników, których bezpośrednim przełożonym jest kierownik departamentu:
SELECT *
FROM employees NATURAL JOIN departments;

-- To jest równoważne takiemu zapytaniu:
SELECT *
FROM employees JOIN departments USING(department_id, manager_id);

-- A zapewne chodziło nam o coś takiego:
SELECT *
FROM employees JOIN departments USING(department_id);

-- Morał: Lepiej nie używać NATURAL JOIN, bo może porównać inne kolumny niż byśmy chcieli.

-- Utożsamianie kolumn w USING i NATURAL JOIN

-- Gdy używamy przecinka i WHERE albo JOIN ON, to w wynikach znajdą się kolumny z obu tabel.
-- Jeśli istnieją kolumny o tej samej nazwie, to obie będą obecne jako osobne kolumny.
-- Gdybyśmy teraz chcieli wskazać taką kolumnę w części WHERE, GROUP BY, ORDER BY albo SELECT, to musimy zrobić to poprzez alias lub nazwę tabeli.
-- Poniższe zapytanie jest błędne: nazwa job_id jest niejednoznacza:
SELECT *
FROM employees e JOIN jobs j ON j.job_id = e.job_id
WHERE job_id = 'ST_CLERK';

-- To jest OK:
SELECT *
FROM employees e JOIN jobs j ON j.job_id = e.job_id
WHERE j.job_id = 'ST_CLERK';

-- Gdy używamy USING albo NATURAL JOIN, to w wynikach znajdzie się tylko jeden egzemplarz kolumny, wg której łączymy.

-- Uwaga dot. Oracla, ale nie dotyczy Postgresa! :
-- W Oracle NIE MOZNA odwoływać się tej kolumny poprzez alias/nazwę tabeli, można tylko bez prefiksu.
-- Poniższe zapytanie jest błędne w Oracle, niepotrzebny kwalifikator (prefiks)
SELECT *
FROM employees e JOIN jobs j USING(job_id)
WHERE j.job_id = 'ST_CLERK';

-- Błędny w Oracle jest również taki zapis (co jest trochę wkurzające... ;-) )
SELECT e.*, j.job_title
FROM employees e JOIN jobs j USING(job_id);

-- Ale oba te zapytania zadziałały w PostgreSQL.

-- To jest OK w obu bazach:
SELECT *
FROM employees e JOIN jobs j USING(job_id)
WHERE job_id = 'ST_CLERK';

--* "Kierunki złączeń" *--
-- Czyli co zrobić, gdy wartości nie uda się dopasować.

-- Sprawdźmy takie zapytanie...
SELECT *
FROM employees e, departments d
WHERE d.department_id = e.department_id;

-- W wynikach jest 106 rekordów, brakuje Kimberely Grant,
-- ponieważ ona w polu department_id ma NULL
SELECT * FROM employees WHERE department_id IS NULL;

-- Istnieją też departamenty, w których nikt nie pracuje.
SELECT DISTINCT department_id FROM employees ORDER BY 1;
SELECT DISTINCT department_id FROM departments ORDER BY 1;

-- A co się dzieje, gdy użyjemy JOIN?
SELECT *
FROM employees JOIN departments USING(department_id);

-- Jeśli używamy składni opartej o JOIN, możemy jednak ten problem rozwiązać.
-- Możemy wybrać rodzaj złączenia i w ten sposób powiedzieć co ma się stać z rekordami, które są w jednej tabeli, a nie mają dopasowania w drugiej tabeli.

-- Złączenia domyślnie są wewnętrzne, tzn. niepasujące rekordy nie są wyświetlane.
-- 106 wyników, nie ma K.Grant, nie też departamentów, w których nikt nie pracuje, np. Payroll:
SELECT * FROM employees INNER JOIN departments USING(department_id);

-- Słowo kluczowe INNER jest opcjonalne i nie zmienia zachowania. Złączenia domyślnie są wewnętrzne.
SELECT * FROM employees JOIN departments USING(department_id);

-- LEFT JOIN wypisuje wszystkie rekordy z lewej tabeli oraz tylko te z prawej tabeli, które udało się dopasować.
-- 107 rekordów, jest K.Grant, widoczne są jej dane, a w kolumnach pochodzących z tabeli departments są u niej wpisane NULL-e:
SELECT * FROM employees LEFT JOIN departments USING(department_id);

-- RIGHT JOIN wypisuje wszystkie rekordy z prawej tabeli oraz tylko te z lewej tabeli, które udało się dopasować.
-- Nie ma K.Grant, ale są departamenty, w których nikt nie pracuje, np. Treasury lub Payroll:
SELECT * FROM employees RIGHT JOIN departments USING(department_id);

-- FULL JOIN wypisuje wszystkie rekordy z obu tabel, dopasowując jeśli się da.
-- Jest K.Grant, są też departamenty, w których nikt nie pracuje, np. Treasury lub Payroll:
SELECT * FROM employees FULL JOIN departments USING(department_id);

-- LEFT, RIGHT oraz FULL to wszystko sa "złączenia zewnętrzne",
-- opcjonalnie można za tymi określeniami dopisać słowo OUTER, które nic nie zmienia:
SELECT * FROM employees LEFT OUTER JOIN departments USING(department_id);
SELECT * FROM employees RIGHT OUTER JOIN departments USING(department_id);
SELECT * FROM employees FULL OUTER JOIN departments USING(department_id);

-- Istnieje jeszcze CROSS JOIN, który tworzy iloczyn kartezjanski, a więc działa dokładnie tak samo, jak przecinek.
-- CROSS JOIN nie używa się w połączeniu z ON ani USING.
SELECT * FROM employees CROSS JOIN departments;

-- Ciekawostka: w MySQL nie dziala FULL JOIN, a w SQLite nie dziala FULL ani RIGHT JOIN
-- MySQL w ogóle bardzo spłyca temat JOINów w porównaniu ze standardem i Oraclem/Postgresem, np. nie rozróżnia INNER od CROSS
-- (w obu przypadkach można dopisać warunek - zadziała jak INNER, albo nie dopsywać - zadziała jak CROSS)

--! Specyfika Oracle !--
-- Wróćmy na chwilę do złączeń realizowanych za pomocą przecinka i WHERE...
-- W bazie Oracle istnieje specjalny zapis, który pozwala osiagnąć efekt taki jak LEFT/RIGHT JOIN bazując na warunkach w WHERE.
-- Jest to unikalna cecha bazy Oracle, nieprzenośna do innych.

-- Efekt analogiczny do RIGHT JOIN. Wszystkie departamenty, a dane pracowników są uzupełniane nullami w miarę potrzeby.
SELECT * FROM employees e, departments d
WHERE e.department_id(+) = d.department_id;

-- Efekt analogiczny do LEFT JOIN. Wszyscy pracownicy, a dane departamentów są uzupełniane nullami w miarę potrzeby.
SELECT * FROM employees e, departments d
WHERE e.department_id = d.department_id(+);

-- Nie da się (+) napisać po obu stronach, więc nie zastąpimy tym FULL JOIN-a

-- Wszystkie złączenia zewnętrzne dałoby się zastąpić odpowiednim użyciem operatora zbiorów UNION ALL - po prostu byłoby więcej pisania.

--* Koniec tematu JOIN *--

--* Operacje teoriomnogościowe, czyli na zbiorach *--
-- UNION ALL, UNION, INTERSECT, EXCEPT

SELECT first_name, last_name FROM employees
UNION ALL
SELECT city, state_province FROM locations;


SELECT city FROM locations; -- 23 rekordy
SELECT country_name FROM countries; -- 25 rekordów
-- Obserwacja: Singapore jest jednoczesnie nazwa miasta i panstwa

-- Mając zapytania, których wyniki są zapisane w tej samej liczbie kolumn o zgodnych typach,
-- możemy te wyniki połączyć za pomocą jednego z operatorów zbiorów:

--# UNION ALL - suma zbiorów z zachowanien ewentualnych duplikatów i kolejności
-- Technicznie, to jest po prostu doklejenie następnych wyników na końcu.
-- To jest tanie (wydajne).
-- Tutaj Singapore występuje dwukrotnie.
SELECT city FROM locations
UNION ALL
SELECT country_name FROM countries;


--# UNION - prawdziwa matematyczna suma zbiorów, z usunięciem powtórzeń.
-- Baza danych ma prawo zmienić kolejność rekordów.
-- W praktyce Oracle sortuje wyniki, ale już PostgreSQL nie!
-- To jest operacyjnie "droższe" niż UNION ALL (może trwać dłużej, może zużyć pamięć)
-- Tutaj Singapore wystąpi tylko raz.
SELECT city FROM locations
UNION
SELECT country_name FROM countries;

--# INTERSECT - przecięcie, część wspólna zbiorów.
-- Tutaj jedynym wynikiem jest Singapore.
SELECT city FROM locations
INTERSECT
SELECT country_name FROM countries;

--# EXPECT - różnica zbiorów.
-- Uwaga: w Oracle nazywa się to (niezgodnie ze standardem) MINUS.
-- Tutaj wynikiem są wszystkie miasta bez Singapuru, bo Singapur występuje też jako nazwa kraju
SELECT city FROM locations
EXCEPT
SELECT country_name FROM countries;

-- Odwrotnie: wszystkie państwa bez Singapuru.
SELECT country_name FROM countries
EXCEPT
SELECT city FROM locations;

-- Gdy używa się operatorów zbiorów, to:
-- * liczba oraz typy kolumn muszą się zgadzać 
--   (na podstawowym poziomie, czyli np. VARCHAR(50) i TEXT będą OK, ale TEXT i NUMERIC już nie)
-- * nazwy kolumn są ustalane w pierwszym SELECT
-- * kolejne SELECTY mogą składać się z klauzul FROM, WHERE, GROUP BY, HAVING
-- * natomiast ORDER BY może wystąpic tylko na samym końcu i posortuje wszystkie części łącznie.

-- Najpierw zobaczymy bogatych, potem biednych, ale żadna z tych części nie będzie w żaden specjalny sposob sortowana
SELECT first_name, last_name, salary
FROM employees
WHERE salary > 10000
UNION ALL
SELECT first_name, last_name, salary
FROM employees
WHERE salary < 5000;

-- Nie wolno wpisać ORDER BY w tym miejscu:
/* SELECT first_name, last_name, salary
FROM employees
WHERE salary > 10000
ORDER BY last_name
UNION ALL
SELECT first_name, last_name, salary
FROM employees
WHERE salary < 5000; */

-- Natomiast ORDER BY na końcu sortuje całe wyniki łącznie.
SELECT first_name, last_name, salary
FROM employees
WHERE salary > 10000
UNION ALL
SELECT first_name, last_name, salary
FROM employees
WHERE salary < 5000
ORDER BY last_name;

-- A jak zachować informację z której części zapytania pochodzi dany rekord?
SELECT first_name, last_name, salary, 'bogaty' AS status_majatkowy
FROM employees
WHERE salary > 10000
UNION ALL
SELECT first_name, last_name, salary, 'biedny'
FROM employees
WHERE salary < 5000
ORDER BY status_majatkowy DESC, last_name, first_name;

-- Jeśli liczy się tylko kolejność, to można po prostu ponumerować:
SELECT first_name, last_name, salary, 1
FROM employees
WHERE salary > 10000
UNION ALL
SELECT first_name, last_name, salary, 2
FROM employees
WHERE salary < 5000
ORDER BY 4, 2, 1;

-- Jeśli umieścimy to wszystko w podzapytaniu, można ukryć kolumny pełniące rolę pomocniczą
SELECT first_name || ' ' || last_name AS kto, salary
FROM (SELECT first_name, last_name, salary, 1
      FROM employees
      WHERE salary > 10000
    UNION ALL
      SELECT first_name, last_name, salary, 2
      FROM employees
      WHERE salary < 5000
    ORDER BY 4, 2, 1) sub;

-- Gdybyśmy chcieli stworzyć "raport" podzielony na dwie częsci z listą bogatych i biednych...
-- Wersja PostgreSQL:
SELECT kto, salary
FROM (
      SELECT 'Bogaci:' AS kto, NULL::numeric AS salary, 1 AS kolejnosc
    UNION ALL
      SELECT '--> ' || first_name || ' ' || last_name, salary, 2
        FROM employees
        WHERE salary > 10000
    UNION ALL
      SELECT 'Biedni:', NULL::numeric, 3
    UNION ALL
      SELECT '--> ' || first_name || ' ' || last_name, salary, 4
        FROM employees
        WHERE salary < 5000
    ORDER BY 3, 1) sub;
    
    
-- Wersja Oracle:
SELECT kto, salary
FROM (
      SELECT 'Bogaci:' AS kto, to_number(NULL) AS salary, 1 AS kolejnosc FROM dual
    UNION ALL
      SELECT '  ' || first_name || ' ' || last_name, salary, 2
        FROM employees
        WHERE salary > 10000
    UNION ALL
      SELECT 'Biedni:', to_number(NULL), 3 FROM dual
    UNION ALL
      SELECT '  ' || first_name || ' ' || last_name, salary, 4
        FROM employees
        WHERE salary < 5000
    ORDER BY 3, 1);    

-- Przykład
-- Za pomocą UNION ALL spróbujmy wypisać informacje o przebiegu zatrudnienia
-- historyczne oraz bieżące

SELECT * FROM job_history;

SELECT employee_id, e.first_name, e.last_name,
    jh.start_date, jh.end_date,
    j.job_title, d.department_name, 1 AS kolejnosc
FROM job_history jh
    JOIN employees e USING(employee_id)
    JOIN jobs j ON j.job_id = jh.job_id
    JOIN departments d ON d.department_id = jh.department_id
UNION ALL
SELECT employee_id, e.first_name, e.last_name,
    e.hire_date, NULL,
    j.job_title, d.department_name, 2
FROM employees e
    JOIN jobs j ON j.job_id = e.job_id
    JOIN departments d ON d.department_id = e.department_id
ORDER BY employee_id, kolejnosc, start_date;


--* CASE *--
-- Tak jak w programowaniu IF albo SWITCH....

-- W PostgreSQL (tak samo w MySQL, SQLite) w SELECT można wpisać wyrażenie typu logicznego
SELECT first_name, last_name, salary, salary >= 10000 AS bogaty
FROM employees;
-- W Oracle tak nie można. Dlatego konstrukcja CASE jest szczególnie ważna w bazie Oracle.
-- Ale także w innych bazach, w tym PostgreSQL, może się przydać:

SELECT first_name, last_name, salary,
    CASE
      WHEN salary >= 10000 THEN 'bogaty'
      ELSE 'biedny'
    END
FROM employees;

-- Między CASE a END można wpisać dowolnie wiele gałęzi WHEN oraz 0 lub 1 galaz ELSE
-- Jako wynik zwrócona będzie wartość z pierwszej gałęzi WHEN, która jest prawdziwa. Kolejność WHEN-ów ma znaczenie.
SELECT first_name, last_name, salary,
    CASE
      WHEN salary >= 15000 THEN 'bardzo bogaty'
      WHEN salary >= 10000 THEN 'bogaty'
      WHEN salary >= 5000 THEN 'sredniak'
      ELSE 'biedny'
    END AS "Status"
FROM employees;

-- w przypadku braku poprawnego WHEN, zwracana jest wartość z ELSE.
-- Gdy nie ma ELSE, to w takim przypadku wynikiem jest NULL.
SELECT first_name, last_name, salary,
    CASE
      WHEN salary >= 15000 THEN 'bardzo bogaty'
      WHEN salary >= 10000 THEN 'bogaty'
      WHEN salary >= 5000 THEN 'średniak'
    END AS "Status"
FROM employees;

SELECT first_name, last_name, commission_pct,
    CASE
      WHEN commission_pct >= 0.3 THEN 'duża prowizja'
      WHEN commission_pct >= 0.2 THEN 'średnia prowizja'
      ELSE 'mała prowizja lub brak'
    END AS "Prowizja"
FROM employees;


-- Czasami warunek w WHEN sprowadza się do sprawdzenia czy pole jest równe jednej wartości, czy innej...
-- Przykładowo, wypiszemy nazwy miesięcy na podstawie daty:
SELECT first_name, last_name, hire_date, extract(month FROM hire_date) as nr_mca,
   CASE
     WHEN extract(month FROM hire_date) = 1 THEN 'styczeń'
     WHEN extract(month FROM hire_date) = 2 THEN 'luty'
     WHEN extract(month FROM hire_date) = 3 THEN 'marzec'
     WHEN extract(month FROM hire_date) = 4 THEN 'kwiecień'
     WHEN extract(month FROM hire_date) = 5 THEN 'maj'
     WHEN extract(month FROM hire_date) = 6 THEN 'czerwiec'
     WHEN extract(month FROM hire_date) = 7 THEN 'lipiec'
     WHEN extract(month FROM hire_date) = 8 THEN 'sierpień'
     WHEN extract(month FROM hire_date) = 9 THEN 'wrzesień'
     WHEN extract(month FROM hire_date) = 10 THEN 'październik'
     WHEN extract(month FROM hire_date) = 11 THEN 'listopad'
     WHEN extract(month FROM hire_date) = 12 THEN 'grudzień'
   END AS miesiac
FROM employees;

-- W takiej sytuacji możemy zastosować inny zapis CASE, gdzie w WHEN nie podaje się warunków logicznych,
-- tylko wartości (to przypomina switch/case z programowania)
SELECT first_name, last_name, hire_date, extract(month FROM hire_date) as nr_mca,
   CASE extract(month FROM hire_date)
     WHEN 1 THEN 'styczeń'
     WHEN 2 THEN 'luty'
     WHEN 3 THEN 'marzec'
     WHEN 4 THEN 'kwiecień'
     WHEN 5 THEN 'maj'
     WHEN 6 THEN 'czerwiec'
     WHEN 7 THEN 'lipiec'
     WHEN 8 THEN 'sierpień'
     WHEN 9 THEN 'wrzesień'
     WHEN 10 THEN 'październik'
     WHEN 11 THEN 'listopad'
     WHEN 12 THEN 'grudzień'
   END AS miesiac
FROM employees;

--! Specyfika Oracle !--
-- W Oracle czasasami da sie zastapic CASE krótszym zapisem DECODE.
-- Ale to będzie mniej przenośne (nie działa np. w PostgreSQL).

SELECT first_name, last_name, decode(last_name, 'King', 'król', 'Gietz', 'to mój kolega', 'nie znam tego gościa') AS txt
FROM employees
ORDER BY employee_id;

SELECT first_name, last_name, hire_date,
   decode(extract(month FROM hire_date),
      1  , 'styczeń'    ,
      2  , 'luty'       ,
      3  , 'marzec'     ,
      4  , 'kwiecień'   ,
      5  , 'maj'        ,
      6  , 'czerwiec'   ,
      7  , 'lipiec'     ,
      8  , 'sierpień'   ,
      9  , 'wrzesień'   ,
      10 , 'październik',
      11 , 'listopad'   ,
      12 , 'grudzień'   ) AS miesiac
FROM employees;


---- Analiza przedzialowa - PARTITION BY ----

-- przypomnienie podzial pracownikow wg job_id i wyliczenie sredniej pensji:
SELECT job_id, count(*) AS ilu, avg(salary) AS srednia
FROM employees
GROUP BY job_id;

-- Aby wyswietlic pracownika w kontekscie jego grupy, np. obok pracownika wyswietlic srednia pensje jego grupy,
-- Mozna uzyc podzapytan
SELECT first_name, last_name, salary, job_id, ilu, srednia
FROM employees
    JOIN (SELECT job_id, count(*) AS ilu, avg(salary) AS srednia
          FROM employees
          GROUP BY job_id) grupy USING(job_id)
ORDER BY job_id, employee_id;

-- PARTITION BY jest konstrukcja bezposrednio adresowana do rozwiazywania tego typu problemow
SELECT first_name, last_name, salary, job_id,
  count(*) OVER (PARTITION BY job_id) as ilu,
  avg(salary) OVER (PARTITION BY job_id) as srednia
FROM employees
ORDER BY job_id, employee_id;

-- Przykład zastosowania: o ile pracownik zarabia więcej lub mniej od średniej na jego stanowisku
SELECT first_name, last_name, salary, job_id,
	round(salary - avg(salary) OVER (PARTITION BY job_id)) AS roznica
FROM employees
ORDER BY job_id, salary DESC;

-- Tutaj zaczniemy uzywac "funkcji okienkowych"
-- row_number podaje numer wiersza w jego grupie. Numeracja liniowa po kolei.
SELECT first_name, last_name, salary, job_id,
	row_number() OVER (PARTITION BY job_id ORDER BY salary DESC)
FROM employees
ORDER BY job_id;

-- Aby użyć tego typu funkcji globalnie dla całej tabeli, trzeba napisać OVER()
-- Przykład: ponumeruj wyniki unikalnymi kolejnymi numerami:
SELECT first_name, last_name, salary, job_id, row_number() OVER () AS nr
FROM employees;

-- rank zwraca jednakowy numer dla rekordow, ktore maja jednakowe wartosci wg sortowania
-- To dziala jak okreslanie miejsca sportowca w zawodach. Np. dwie osoby dostaja srebrny medal, bo mialy jednakowy wynik
-- I wtedy brazowego medalu sie nie przyznaje...
SELECT first_name, last_name, salary, job_id,
	rank() OVER (PARTITION BY job_id ORDER BY salary DESC)
FROM employees;

-- A dla całej tabeli? Musimy podać kolejność, ale nie robimy podziału
SELECT first_name, last_name, salary, job_id,
	rank() OVER (ORDER BY salary DESC)
FROM employees;


-- dense_rank - Tutaj podobnie rekordy z ta sama wartoscia uzyskaja ten sam numer
-- ale nastepni w kolejnosci dostana nastepny numer, bez zadnych przerw.
-- Tutaj nawet gdyby byly dwa srebrne medale, to bedzie tez przyznany brazowy
SELECT first_name, last_name, salary, job_id,
	dense_rank() OVER (PARTITION BY job_id ORDER BY salary DESC)
FROM employees;

SELECT first_name, last_name, salary, job_id,
	dense_rank() OVER (ORDER BY salary DESC)
FROM employees;


-- ntile(N) podaje info, w ktorej czesci znalazlby sie wynik, gdybysmy posortowane wyniki podzielili na N rownych czesci
-- Jesli uzyjemy tego z polaczeniu z PARTITION BY, to kazda grupe dzieli na N czesci i infromuje w ktorej czesci znajduje sie konkretny rekord

-- Tutaj dowiemy sie czy pracownik nalezy do bogatszej polowy (1) na swoim stanowiksu, czy do biednejszej polowy (2)
-- Przy czym to dziala "technicznie" i w przypadku rownych pensji (zob. AD_VP) pracownicy o tej samej pensji moga trafic do roznych grup, bo akurat tu przebiega granica
SELECT first_name, last_name, salary, job_id,
	ntile(2) OVER (PARTITION BY job_id ORDER BY salary DESC)
FROM employees;

-- Tutaj dzielimy pracownikow na 10 czesci wg pensji.
SELECT first_name, last_name, salary,
	ntile(10) OVER (ORDER BY salary DESC)
FROM employees;

SELECT first_name, last_name, salary,
ntile(10) OVER (ORDER BY salary DESC)
FROM employees
ORDER BY last_name, first_name;


-- odwolanie do wartosci, ktora wystapila wczesniej, (do "sasiada")
SELECT first_name, last_name, salary, job_id,
	lag(salary, 1) OVER (PARTITION BY job_id ORDER BY salary DESC) AS poprzedni
FROM employees;

SELECT first_name, last_name, salary, job_id,
	lag(salary, 1) OVER (ORDER BY salary DESC) AS poprzedni
FROM employees;

-- Przykladowo: o ile pensja pracownika jest mniejsza od poprzedniego na tym samym stanowisku:
SELECT first_name, last_name, salary, job_id,
    lag(salary, 1) OVER (PARTITION BY job_id ORDER BY salary DESC) - salary AS roznica
FROM employees;

-- albo nie do sasiada tylko do rekordu ktory wystapil x pozycji temu
-- Tutaj już bez podziału na stanowiska
SELECT first_name, last_name, salary, job_id,
	lag(salary, 2) OVER (ORDER BY salary DESC)
FROM employees;

-- zagladanie do rekordow nastepnych
SELECT first_name, last_name, salary, job_id,
	lead(salary, 1) OVER (PARTITION BY job_id ORDER BY salary DESC)
FROM employees;

-- przyklad bez partition by, samo zastosowanie f.okienkowej
-- o ile mniej zarabiam od poprzedniego pracownika na liscie
SELECT first_name, last_name, salary,
	abs(salary - lag(salary, 1) OVER (ORDER BY salary DESC)) AS roznica
FROM employees;

-- Odwołanie do pierwszego rekordu danej grupy
SELECT first_name, last_name, salary, job_id,
	first_value(salary) OVER (PARTITION BY job_id ORDER BY salary DESC)
FROM employees;

-- Dla pracownika info kto na jego stanowisku zarabia najwięcej
SELECT first_name, last_name, salary, job_id,
	first_value(first_name || ' ' || last_name)
		OVER (PARTITION BY job_id ORDER BY salary DESC)
FROM employees;

SELECT first_name, last_name, salary, job_id, hire_date,
	lag(first_name || ' ' || last_name, 1)
		OVER (PARTITION BY job_id ORDER BY salary DESC)
FROM employees
ORDER BY hire_date;


-- Dla każdego pracownika podaj info ile dni minęło od zatrudnienia poprzedniej osoby do zatrudnienia bieżącej osoby.
SELECT first_name, last_name, hire_date,
	hire_date - lag(hire_date, 1) OVER (ORDER BY hire_date) AS roznica_czasu
FROM employees;


------------  zapytania zagnieżdżone  =  podzapytania ----------

-- Zapytanie (caly SELECT) moze byc wziety w nawiasy () i potraktowany jak wyrazenie, które zwraca wynik
-- i ktorego mozna uzyc jako czesci wiekszego zapytania.
-- Te zagniezdzone SELECT-y moga byc uzywane w roznych miejscach glownego zapytania.
-- Moga zwracac w wyniku:
--  * pojedyncza wartosc (podzapytania skalarne)
--  * tabele
--  * szczegolnym przypadkiem tabeli moze byc tabela jednolinijkowa albo jednokolumnowa...

-- Podzapytania moga byc jeszcze skorelowane  z zapytaniem glownym (odwouja sie do danych z zewn. zapytania),
-- albo nieskorelowane (sa samodzielne i moglyby byc wykonane niezaleznie od zapytania zewn.).

-- Obok pracownika wypisz srednia pensje w całej firmie i oblicz różnicę między pensją pracownika a średnią firmy
SELECT first_name, last_name, salary,
	(SELECT 2+2) AS cztery,
	(SELECT avg(salary) FROM employees) AS srednia,
	round(salary - (SELECT avg(salary) FROM employees)) AS roznica
FROM employees;
-- Powyższe zapytania są "nieskorelowane", czyli mogą być wyliczone raz, a nie osobno dla każdego wiersza.

-- Dla kazdego pracownika wypisz srednia pensje na jego stanowisku i w jego departamencie.
SELECT first_name, last_name, salary,
  (SELECT avg(salary) FROM employees wewn WHERE wewn.job_id = zewn.job_id) AS "średnia stanowiska",
  (SELECT avg(salary) FROM employees wewn WHERE wewn.department_id = zewn.department_id) AS "średnia departamentu"
FROM employees zewn;
-- Zapytanie jest "skorelowane", bo wewnatrz odwolujemy sie do wartosci zewn.department_id
-- ustalonej przez zapytanie zewnetrzne
-- Oracle dla poszczegolnych wartosci department_id wykonuje zapytania:
-- SELECT avg(salary) FROM employees WHERE department_id = 90;
-- SELECT avg(salary) FROM employees WHERE department_id = 100;
-- itd.

-- Czesto za pomoca podzapytan mozna robic to samo, co da sie zrobic za pomoca JOIN
SELECT first_name, last_name, salary,
   (SELECT department_name FROM departments WHERE department_id = emp.department_id) AS dep_name
FROM employees emp;

SELECT first_name, last_name, salary, department_name
FROM employees emp LEFT JOIN departments dep ON dep.department_id = emp.department_id;

-- Przy okazji: Aby zobaczyć info nt kosztu zapytania, można użyć polecenie explain, w SQL Developerze robi się to klawiszem F10


-- Na tej samej zasadzie jak w SELECT, zadzialalyby zapytania skalarne w WHERE czy nawet ORDER BY.
-- Prosty przyklad: wypisz pracownikow, ktorzy zarabiaja ponizej sredniej w firmie
SELECT first_name, last_name, salary
FROM employees
WHERE salary < (SELECT avg(salary) FROM employees)
ORDER BY employee_id;
-- To jest zapytanie skalarne (bo zwraca jeden wynik)
-- NIESKORELOWANE (bo podzapytanie nie odwoluje sie do danych z zapytania glownego) i moze byc wykonane niezaleznie, tylko jeden raz

-- To teraz znajdzmy pracownikow zarabiajacych ponizej sredniej na ICH stanowisku
-- To juz bedzie SKORELOWANE
SELECT first_name, last_name, job_id, salary
FROM employees zewn
WHERE salary <= (SELECT avg(salary) FROM employees WHERE job_id = zewn.job_id)
ORDER BY employee_id;

-- Podzapytania tabelaryczne ("wielowierszowe") we FROM
-- Zapytanie we FROM zawsze sa nieskorelowane

-- Takie zapytanie dla stanowiska wylicza ilosc pracownikow oraz srednia pensje
SELECT job_id, job_title, count(employee_id) AS ilu, avg(salary) AS srednia
FROM jobs JOIN employees USING(job_id)
GROUP BY job_id, job_title;


-- Cos prostego
-- We FROM glownego zapytania zamiast zwyklej tabeli umieszczamy podzapytanie, ktore produkuje tabele.
SELECT job_title, ilu, round(srednia, 1)
FROM (SELECT job_id, job_title, count(employee_id) AS ilu, avg(salary) AS srednia
     FROM jobs JOIN employees USING(job_id)
     GROUP BY job_id, job_title) podzapytanie
WHERE ilu >= 10
ORDER BY job_title;

-- Najczesciej tabele uzyskane za pomoca podzapytac laczy sie z innym i w zapytaniu glowym.

-- Moge uzyc wynikow tego zapytania jako zrodla dla nastepnego zapytania,
-- ktore np. obok pracownika wypisze dane jego stanowiska
-- v1: jako podzapytanie we FROM
SELECT e.first_name, e.last_name, e.salary, job_info.job_title, job_info.ilu, job_info.srednia
FROM employees e JOIN
    (SELECT job_id, job_title, count(employee_id) AS ilu, avg(salary) AS srednia
     FROM jobs JOIN employees USING(job_id)
     GROUP BY job_id, job_title) job_info USING(job_id)
ORDER BY e.employee_id;

-- v2: za pomoca WITH
WITH job_info AS (
     SELECT job_id, job_title, count(employee_id) AS ilość, avg(salary) AS srednia
     FROM jobs JOIN employees USING(job_id)
     GROUP BY job_id, job_title
)
SELECT e.first_name, e.last_name, e.salary, job_info.job_title, job_info.ilość, job_info.srednia
FROM employees e JOIN job_info USING(job_id)
ORDER BY e.employee_id;

-- Alternatywnie można by użyć podzapytań skalarnych w SELECT - ale to będzie gorsze podejście... i wydaje się najmniej wydajne
SELECT e.first_name, e.last_name, e.salary
   , (SELECT job_title FROM jobs WHERE job_id = e.job_id) AS job_title
   , (SELECT count(employee_id) FROM employees WHERE job_id = e.job_id) AS ilość
   , (SELECT avg(salary) FROM employees WHERE job_id = e.job_id) AS średnia
FROM employees e
ORDER BY e.employee_id;

-- Alternatywnie można też użyć zwykego JOIN i odpowiednio grupowac, ale to tez wyjdzie brzydko
SELECT e1.first_name, e1.last_name, e1.salary, j.job_id, j.job_title,
   count(e2.employee_id) AS ilu, avg(e2.salary) AS srednia
FROM employees e1
    JOIN jobs j ON j.job_id = e1.job_id
    JOIN employees e2 ON e2.job_id = j.job_id
GROUP BY j.job_id, j.job_title, e1.employee_id, e1.first_name, e1.last_name, e1.salary;


-- Przykład z dwoma podzapytaniami - pacownik, jego stawisko i jego departament
-- Wersja zagnieżdżona
SELECT e.first_name, e.last_name, e.salary
	,job_info.job_title
	,job_info.ilu AS job_ilosc
	,round(job_info.srednia, 1) AS job_srednia
	,dep_info.department_name
	,dep_info.ilu AS dep_ilosc
	,round(dep_info.srednia, 1) AS dep_srednia
FROM employees e
    LEFT JOIN (
		   SELECT job_id, job_title, count(employee_id) AS ilu, avg(salary) AS srednia
           FROM jobs JOIN employees USING(job_id)
           GROUP BY job_id
	   ) job_info USING(job_id)
    LEFT JOIN (
		   SELECT department_id, department_name, count(employee_id) AS ilu, avg(salary) AS srednia, city
           FROM departments
		       LEFT JOIN employees USING(department_id)
			   LEFT JOIN locations USING(location_id)
           GROUP BY department_id, city
	   ) dep_info USING(department_id)
ORDER BY e.employee_id;

-- wersja z WITH
WITH job_info AS
	(SELECT job_id, job_title, count(employee_id) AS ilu, avg(salary) AS srednia
    FROM jobs JOIN employees USING(job_id)
    GROUP BY job_id),
dep_info AS
	(SELECT department_id, department_name, count(employee_id) AS ilu, avg(salary) AS srednia, city
    FROM departments
		LEFT JOIN employees USING(department_id)
		LEFT JOIN locations USING(location_id)
	GROUP BY department_id, city)
SELECT e.first_name, e.last_name, e.salary
	,job_info.job_title
	,job_info.ilu AS job_ilosc
	,round(job_info.srednia, 1) AS job_srednia
	,dep_info.department_name
	,dep_info.ilu AS dep_ilosc
	,round(dep_info.srednia, 1) AS dep_srednia
FROM employees e
    LEFT JOIN job_info USING(job_id)
    LEFT JOIN dep_info USING(department_id)
ORDER BY e.employee_id;

-- Podzapytania w WHERE
-- podobnie jak w SELECT podzapytania mogą być skorelowane lub nieskorelowane
-- podzapytania mogą zwracać pojedynczą wartość (skalarne)
-- Ale dodatkowo sens mają podzapytania, które zwracają wynik jako kolumnę z wartościami ("lista wartości")
-- - można ich użyć w połączeniu z operatorami logicznymi IN, ALL, ANY, EXISTS

-- Przykłady
-- podzapytanie skalarne, nieskorelowane
-- Wypisz pracowników, których pensja jest wyższa od średniej
SELECT first_name, last_name, job_id, salary
FROM employees
WHERE salary > (SELECT avg(salary) FROM employees);

-- podzapytanie skalarne, skorelowane
-- Wypisz tych, którzy mają średnią pensję wyższą niż średnia na ich stanowisku
SELECT first_name, last_name, job_id, salary
FROM employees zewn
WHERE salary > (SELECT avg(salary)
                FROM employees wewn
                WHERE wewn.job_id = zewn.job_id)
ORDER BY employee_id;

-- Dodatkowy przykład nt przedziałów czasowych
SELECT first_name, last_name, hire_date
FROM employees
WHERE hire_date BETWEEN 
	(SELECT min(start_date) FROM job_history) 
	AND (SELECT max(start_date) FROM job_history);

-- Przykład podzapytania z IN
-- To samo da się zrobić inaczej, ale po prostu prezentuję działanie IN
-- Wypisz te depratamenty, w których ktoś pracuje

SELECT department_id, department_name
FROM departments
WHERE department_id IN (SELECT DISTINCT department_id FROM employees);

-- Można także porównywać całe krotki
-- Przykład: znajdź pracowników, w których departamencie istnieje
-- inny pracownik zarabiający tyle samo
SELECT first_name, last_name, salary, department_id
FROM employees zewn
WHERE (salary, department_id) IN
  (SELECT salary, department_id
   FROM employees wewn
   WHERE zewn.employee_id <> wewn.employee_id);


-- Wypisz departamenty, w których ktoś zarabia między 9 a 10 tys.
SELECT department_id, department_name
FROM departments dep
WHERE EXISTS (SELECT employee_id FROM employees
              WHERE department_id = dep.department_id
                 AND salary BETWEEN 9000 AND 10000);

-- Dla operatora EXISTS liczy się tylko to, czy podzapytanie zwróciło jakikolwiek wynik.
-- Dlatego obowiązuje konwencja pisania SELECT 1 w podzapytaniach używanych w ramach EXISTS
-- To może zwiększyć wydajność. A EXISTS i tak jest wydajnym rozwiazaniem, bo wyniki podzapytania nie musza byc wczytywane do konca, wystarczy ze pojawi sie pierwszy wynik.
-- Czesto EXISTS odwoluje sie tylko do indeksow, a nie musi czytac docelowej tabeli.
SELECT department_id, department_name
FROM departments dep
WHERE EXISTS (SELECT 1 FROM employees
              WHERE department_id = dep.department_id
                 AND salary BETWEEN 9000 AND 10000);

-- Dodatkowe wyjaśnienia:
-- To podzapytanie czasami zwraca jakieś rekordy -> to wtedy EXSISTS da wynik TRUE
SELECT employee_id FROM employees
WHERE department_id = 80
AND salary BETWEEN 9000 AND 10000;

SELECT 1 FROM employees
WHERE department_id = 80
AND salary BETWEEN 9000 AND 10000;

-- A czasami wynik jest pusty -> wtedy EXISTS daje wynik FALSE
SELECT employee_id FROM employees
WHERE department_id = 90
AND salary BETWEEN 9000 AND 10000;

-- To akurat da się też zapisać za pomoca IN
SELECT department_id, department_name
FROM departments dep
WHERE department_id IN (SELECT department_id FROM employees
              WHERE salary BETWEEN 9000 AND 10000);

--- ANY i ALL - tzw. kwantyfikatory

-- Wypiszmy pracowników, którzy zarabiają najwięcej na swoim stanowisku
-- Zwrocmy uwage na wiceprezesow 17000  - oboje zostana wyswietleni, bo maja jednakowe maksymalne pensje na swoim stanowisku
SELECT first_name, last_name, job_id, salary
FROM employees zewn
WHERE salary >= ALL (SELECT salary
                FROM employees wewn
                WHERE wewn.job_id = zewn.job_id)
ORDER BY employee_id;

-- Wypiszmy pracowników, którzy zarabiają więcej niż ktokolwiek na ich stanowisku
-- ANY i SOME to jest to samo
SELECT first_name, last_name, job_id, salary
FROM employees zewn
WHERE salary > ANY (SELECT salary
                FROM employees wewn
                WHERE wewn.job_id = zewn.job_id)
ORDER BY employee_id;

-- BTW te przykłady dało się zrobić inaczej, np.:
SELECT first_name, last_name, job_id, salary
FROM employees zewn
WHERE salary = (SELECT max(salary)
                FROM employees wewn
                WHERE wewn.job_id = zewn.job_id)
ORDER BY employee_id;
