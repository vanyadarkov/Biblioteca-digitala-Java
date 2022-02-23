## LibroData - Gestiunea si livrarea de carti electronice

Realizat de: Catruc Ionel 322CBa

# Introducere
Odată cu digitalizarea a mare parte din industrii, și industria distriburii cărților a fost modificată. În acest context, prezentăm mai departe cum funcționează entitățile ce modelează procesul de distribuire a cărților electronice.
Entitatea **Book** este elementul de bază al domeniului. Ea este caracterizată de următoarele:
- ID (identificator unic) - name (numele de publicare al cărții)
- subtitle (subtitlu al cărții, opțional)
- ISBN (identificat unic al cărții, format string, e.g. 9782806231789, cunoscut de entități externe)
- pageCount (număr de pagini) - keywords (cuvinte cheie ale cărții, separate prin ; )
- languageID (limba în care este scrisă)
- createdOn (data adăugării în sistem a cărții)
- authors (listă de autori)

Mai departe, definim Entitatea **Language**. Ea are următoarele proprietăți:
- ID (identificator unic)
- code (cod de limbă, prescurtare a limbii)
- name

Entitatea **Author**, cu următoarele caracteristici:
- ID (identificator unic)
- firstName
- lastName

Entitatea **PublishingBrand** – orice carte poate sau nu să treacă în publicare printr-un brand care se ocupă de publicare – cu următoarele proprietăți:
- ID (identificator unic)
- name
- books (listă de cărți publicate de un brand în parte)

Entitatea **EditorialGroup** – orice carte care poate sau nu să treacă înainte de publicare printr-un brand care se ocupa de editarea cărții – cu următoarele proprietăți:
- ID (identificator unic)
- name
- books (listă de cărți publicate de un brand în parte)

Mai departe prezentăm entitatea **PublishingRetailer**, care este efectiv firma care publică cărțile în magazinele online, spre exemplu – Google, Amazon, Apple, etc. Ei sunt cei care afișează cărțile pe platformele lor, și au următoarele proprietăți:
- ID (identificator unic)
- name
- publishingArtifacts (listă de entități publicabile
- tip IPublishingArtifact)
- countries (listă cu țările în care respectivul retailer publică)

Logica este următoarea – către **PublishingRetailer** pot fi publicate cărți direct de la autori, prin entitatea **Book**, de la **EditorialGroup** – care implică publicarea către retailer a tuturor cărților ce sunt editate la un editorial group; respectiv de la **PublishingBrand** – cărțile dintr-un brand de publicare merg automat și la retailerii unde acel **PublishingBrand** aparține.
Interfața **IPublishingArtifact** conține o metodă `Publish()` ce întoarce un **String**, care conține metadata respectivului artifact.
Mențiune – o carte poate fi publicată unui **PublishingRetailer** prin oricare din cele 3 canale – direct de autor, prin editorial group, respectiv prin publishing brand.

Entitatea **Countries**, care are următoarele proprietăți:
- ID (identificator unic)
- countryCode (cod pentru țară, din două litere – e.g., RO pentru România)

# Fisierele pentru initializare
În contextul acestei teme, vi s-au pregătit câteva seturi de date pe baza cărora să inițializăm biblioteca virtuală de cărți. Acestea se vor importa printr-un set de fișiere de inițializare – fișiere de intrare.
Primiți așadar următoarele fișiere, separatorul este “###”:
- `books.in` – inițializarea cărților în sistem, header-ul fișierului sunt proprietățile clasei Book, fără proprietarea authors
- `languages.in` – inițializarea limbilor în sistem, header-ul fișierului sunt proprietățile clasei Language
- `authors.in` – inițializarea autorilor în sistem, header-ul fișierului sunt proprietățile clasei Authors
- `books-authors.in` – inițializarea asocierilor dintre cărți și autori, headerul fișierului sunt idurile cărții și , respectiv al autorului
- `editorial-groups.in` – inițializarea grupurilor de editare din sistem, header-ul fișierului sunt proprietățile clasei EditorialGroup
- `publishing-brands.in` - inițializarea grupurilor de publicare din sistem, header-ul fișierului sunt proprietățile clasei PublishingBrand
- `editorial-groups-books.in` – inițializarea asocierilor dintre grupuri de editare și autori, headerul fișierului sunt id-urile grupului de editare și , respectiv al autorului
- `publishing-brands-books.in` – inițializarea asocierilor dintre grupuri de publicare și autori, headerul fișierului sunt id-urile grupului de publicare și , respectiv al autorului
- `publishing-retailers.in` – inițializarea retailerilor din sistem, header-ul fișierului sunt proprietățile clasei PublishingRetailer
-  `countries.in` – inițializarea țărilor din sistem, header-ul fișierului sunt proprietățile clasei Country
- `publishing-retailers-countries.in` – inițializarea asocierilor dintre retailer și țări, headerul fișierului sunt id-urile retailerului și, respectiv, al țării
- `publishing-retailers-books.in` - inițializarea asocierilor dintre retailer și cărți, headerul fișierului sunt id-urile retailerului și, respectiv, al cărții
- `publishing-retailers-editorial-groups.in` - inițializarea asocierilor dintre retailer și grupuri de editare, headerul fișierului sunt id-urile retailerului și, respectiv, al grupului de editare
- `publishing-retailers-publishing-brands.in` - inițializarea asocierilor dintre retailer și grupuri de publicare, headerul fișierului sunt id-urile retailerului și, respectiv, al grupului de publicare

### Constructorii claselor principale

Primesc **String[]** - linia dupa **split("###")** si extrag din ea fiecare parametru pentru a initializa variabilele membru. Pe langa asta, unele clase principale au **constructori auxiliari dupa ID** si **metoda equals**(care compara dupa ID, care pentru fiecare obiect este unic). Asta a fost folosit in cazul in care am nevoie sa folosesc **ArrayList.indexOf** care foloseste metoda equals.

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
Clasa de autori. Equals, care compara dupa id, pentru a imi usura cautarea unui autor la momentul adaugarii catre carte. toString - transforma in string un Autor si deoarece in fisierele .in unii autori nu au FirstName sau LastName, am verificari pentru a nu adauga whitespaces.

### Book
In **constructor** se face initializarea **calendarului**. Ii specific **formatul** stringului de unde extrag data, o parsez catre **Date**, imi creez un calendar si ii setez **timpul**. Publish se ocupa de publicarea unei carti. El concateneaza xml, apeleaza **toString** al cartii cu parametrul 1 - care e **numarul de taburi** necesari sa ii punem pentru fiecare parametru mai jos de xml ca sa **respectam formatul** cerut. La sfarsit, din stringul final **elimin "[" si "]"**, care pot aparea la afisarea **autorilor**.

### EditorialGroup, PublishingBrand
As dori sa explic metoda **Publish**, care apeleaza **toString** din cadrul lor. Pentru fiecare carte din **Group** sau **Brand** se apeleaza metoda **toString** a cartii cu parametrul 3 ca **numar de taburi** pentru a respecta **formatul**. Am recurs la aceasta metoda pentru **nu a avea cod duplicat**.

### Copyright
Drepturile asupra conditiei acestui proiect, enuntului cat si fiserelor de intrare apartin echipei POO 2021, UPB.