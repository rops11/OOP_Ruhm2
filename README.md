# OOP 2. Rühmatöö
Autorid: Robert Ivask ja Vladimir Makarenkov
<br><br>
Meie tehtud sõna mängu inspiratsiooniks oli Wordle.
<br><br>
Kuidas käivitada? -> Mängu saab käivitada command promptis.
1) Ava cmd.exe
2) Liigu cd käsuga mängu kausta
3) Kirjuta java SõneMäng

Kuidas mängida? -> Mängija peab ära arvama õige sõna. 
* Suur täht tähistab äraarvatud tähte
* Väike täht tähistab tähte, mis on vales kohas
* "_" tähistab, et sellist tähte pole sõnas

Klassid
* SõneMäng on peaklass, milles toimub mängu tsükkel.
* Mängija klass hoiab mängija võite ja kaotusi. Selle kõige olulisem meetod on uusMäng(String arvatavSõna, int katseid), mis vahetab arvatavat sõna ja nullib katsete arvu.
* SõneAnalüsaator klass hoiab õiget vastus ja katsete arvu. Selle kõige olulisem meetod on kontrolliVastust(String pakkumine), mis tagastab kas mängija vastas õigesti ning annab vihjeid.
* SõneLugeja klass hoiab sõnade failiteed. Selle olulisemad meetodid on loeFail(), mis tagastab mängus kasutatavate sõnade massiivi ja arvatavSõna(), mis tagastab suvaliselt ühe sõna sõnade massiivist.

Projekti töökäik
* projekti põhjalik kirjeldus, kus on kirjas programmi eesmärk ja selgitus programmi üldisest tööst, vajadusel lühike kasutusjuhis;
* Projekti tegemise alguses oli vaja teha selgeks programmi idee ja põhimõte. Peale seda teha kindlaks, mis klassid ja meetodid on vaja luua. Peale seda jagasime oma vahel tööülesanded ja hakkasime klasse looma. Robert tegeles sõneMängu,sõneAnalüsaatori ja Mängija klassidega. Vladimir tegeles sõneLugeja ja sõneMängu klassidega. Roberti ülesanne oli seadistada mängu tööpõhimõte sõneAnalüsaator klassis, Vladimiri ülesanne oli lugeda failist adnmed ja seadistada need mängu tsüklisse. Lõpuks tegime mõlemad viimistlused mängu tsüklis, et see oleks nii huvitav, kui ka mugav ja arvestaks erinevate mängija valikutega.
* Robert tegeles sõneAnalüsaatori ja Mängija klassidega ja kõikide meetoditega nendes ning realiseeris neid sõneMäng klassis. Kokku selleks kulus umbes 6 tundi. Vladimir tegeles sõneLugeja klassiga ja kõikide meetoditega selles ning realiseeris neid sõneMäng klassis. Kokku selleks kulus umbes 5 tundi.

Tegemise mured
* Üldiselt projekti tegemisel ei esinenud probleeme ning see valmis sujuvalt.

Hinnang oma töö lõpptulemusele (millega saite hästi hakkama ja mis vajab arendamist)
* Tuli hästi välja ülesannete jagamine, nii, et programmi koostamine möödus ladusasti. Samuti ka programmi planeeritud põhimõte sai täidetud.
* Töö valmimisel tekkis uusi ideid, mida ei realiseeritud, kuna nad polnud algses projekti plaanis.

Selgitus ja/või näited, kuidas programmi osi eraldi ja programmi tervikuna testisite ehk kuidas veendusite, et programm töötab korrektselt.
* Testisime alguses programmi meetodeid eraldi, peale seda panime SõneMäng klassi suvalised sõnad käsitsi, et kontrollida programmi üldist tööd. Peale seda ühendasime peameetodi tsüklis mängu meetodid failist saadavate sõnadega ja vaatasime programmi tööd.

