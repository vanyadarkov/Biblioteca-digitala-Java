# Readme Tema 1 POO
## LibroData - Gestiunea si livrarea de carti electronice

Student: Catruc Ionel 322CBa

### Constructorii claselor principale

primesc **String[]** - linia dupa **split("###")** si extrag din ea fiecare parametru pentru a initializa variabilele membru. Pe langa asta, unele clase principale au **constructori auxiliari dupa ID** si **metoda equals**(care compara dupa ID, care pentru fiecare obiect este unic). Asta a fost folosit in cazul in care am nevoie sa folosesc **ArrayList.indexOf** care foloseste metoda equals.	

### Initialiser

Clasa care are ca variabile membru ArrayList de fiecare tip necesar in tema. Initializarea lor are loc la invocarea constructorului Initialiser. Contine metode necesare pentru a initializa fiecare instanta si a face legaturile necesare pentru o functionare ceruta.

 - **public List<String[]> readFile(String filePath)** : citeste din fisierul cu calea **filePath**(relativa catre proiect) si **intoarce o lista** a carei fiecare element e o **linie reprezentata prin String[]**, in care la fiecare index se afla cate un **parametru** necesar initializarii.
 - **public void addAuthorsToBook(String[] parameters)** : primeste ca si cum ar veni o linie cu parametrii necesari pentru a adauga un author unei carti. Se creeaza obiecte auxiliare de tip Book si author necesare in cautarea prin ArrayList al fiecarui tip. Cautarea are loc prin folosirea indexOf si get, ambele folosesc metoda equals. Equals este override in book si author pentru a compara dupa ID (deoarece este unic).
 - **public void addBookToSomewhere(String[] parameters, String whereToAdd)** : adauga carte undeva, unde acel undeva poate fi **"Editorial Group", "Publishing Brand" sau "Publishing Retailer"**. Ideea este urmatoarea - se **cauta cartea in ArrayList** de books, dupa care, in functie de whereToAdd stie unde sa adauge, cauta acel Where in lista sa corespunzatoare si deja adauga cartea(folosind **addBook** sau **addPublishingArtifact**).
 - **addCountryToPublishingRetailer** : Se foloseste dupa aceleasi principii ca mai sus. Cauta retailerul, cauta country necesar (folosinduse de aux) si dupa adauga tara in arraylist-ul corespunzator.
 - **public void addArtifactToPublishingRetailer(String[] parameters, String whichArtifactIs)** : adauga Artifact la **publishingRetailer**. Cauta retailerul, apoi in functie de ce artefact este (**whichArtifactIs poate fi "Editorial Group" or "Publishing Brand"**). Cauta acest **Artifact** in **ArrayList-ul** unde el se afla si il adauga la **ArrayList-ul publishingArtifacts**.

### Administration

Clasa de administrare a obiectelor din cadrul temei. Aici sunt implementate metodele cerute cat si o metoda de a scrie la output file rezultatele obtinute in urma testelor. Are ca **variabile membru Arraylist** de **publishingRetailers** si **languages**.'

 - **public ArrayList getBooksForPublishingRetailerID(int publishingRetailerID)** : Obtine toate cartile unice al unui publishingRetailer dupa ID sau. **Parcurge Artifacturile** sale, daca e book, **verifica** daca ea deja nu a fost adaugata la lista finala si dupa o adauga. Daca e altceva(**brand sau group**) **extrage cartile sale** intr-un auxList. Parcurge aceasta auxList si verifica daca cartea nu a fost deja adaugata dupa care o adaug, cu scopul de a **obtine doar carti unice**.
 - **public ArrayList<Language> getLanguagesForPublishingRetailerID** : Obtine toate limbile in care un PublishingRetailer isi publica cartile. **Cautam** retailer-ul si ii parcurgem toate **cartile**. Pentru fiecare carte, **extragem** languageID si il **cautam** in obiectele de tip language, il extragem si daca nu a fost adaugat la lista finala il adaugam.
 - **public ArrayList getCountriesForBookID(int bookID)** : Obtine **lista de tari** in care ajunge cartea cu **bookID**. Deoarece **o carte** se poate afla **la mai multi retailer**, trebuie sa verificam daca ea nu se afla in lista de carti a fiecarui retailer. **Extragem** cartile fiecaruia cu ajutor **getBooksForPublishingRetailerID** si vedem, daca cartea este acolo, adaugam la lista finala de tari toate tarile retailerului unde am gasit cartea. **ArrayList.addAll** adauga doar elementele **unice**.
 - **public ArrayList getCommonBooksForRetailerIDs** : **Obtin in 2 liste** cartile ambilor retaileri, intr-un **hashSet** bag toate cartile **retailer2**, parcurg cartile **retailer1**. Daca hashSet meu **contains**(**cartea din retailer1**) adauga la lista finala aceasta carte. **HashSet.contains e de complexitate O(1)**.
- **public ArrayList getAllBooksForRetailerIDs** : Obtin o lista cu **reuniunea** cartilor a doi retaileri. Salvez intr-un **HashSet** una din liste si fac **addAll(cealalta lista)** la acest hashSet. **addAll nu va adauga dublicate**. La sfarsit fac **conversie** la ArrayList a acestui **HashSet**.
- **public void writeToOutputFile** : Scrie la **output** rezultatul metodelor de mai sus. Poate scrie rezultatul reprezentat de un **ArrayList** de **book**, **languages** sau **Countries**. Pentru **book** va face **publish** de fiecare carte, va adauga strigul de la publish intr-un StringBuffer si o va scrie la output. Daca e **languages**, la fel in StringBuffer va adauga **numele** limbei iar pentru **countries** va baga la SB **codul** tarii.

### Author
Clasa de autori. In mare parte e ca in cerinta, cu unele modificari personale pentru a imi usura viata. Equals, care compara dupa id, pentru a imi usura cautarea unui autor la momentul adaugarii catre carte. toString - transforma in string un Autor si deoarece in fisierele .in unii autori nu au FirstName sau LastName, am verificari pentru a nu adauga whitespaces.
### Book
In **constructor** se face initializarea **calendarului**. Ii specific **formatul** stringului de unde extrag data, o parsez catre **Date**, imi creez un calendar si ii setez **timpul**. Publish se ocupa de publicarea unei carti. El concateneaza xml, apeleaza **toString** al cartii cu parametrul 1 - care e **numarul de taburi** necesari sa ii punem pentru fiecare parametru mai jos de xml ca sa **respectam formatul** cerut. La sfarsit, din stringul final **elimin "[" si "]"**, care pot aparea la afisarea **autorilor**.

### EditorialGroup, PublishingBrand
Practic totul este ca in cerinta. As dori sa explic metoda **Publish**, care apeleaza **toString** din cadrul lor. Pentru fiecare carte din **Group** sau **Brand** se apeleaza metoda **toString** a cartii cu parametrul 3 ca **numar de taburi** pentru a respecta **formatul**. Am recurs la aceasta metoda pentru **nu a avea cod duplicat**.