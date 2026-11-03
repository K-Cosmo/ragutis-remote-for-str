# Privacy policy / Datenschutzhinweise — Ragutis Remote for SoundTouch Reborn

**Current app: Ragutis STR / Ragutis Remote for SoundTouch Reborn, public GitHub version 0.3.4 (2026-11-03).** Independent/unofficial companion app, not operated by Bose Corporation or the STR project. This document describes the app's observable design and known third-party boundaries. It does **not** pre-certify Google Play's Data safety questionnaire, which needs its own shipped-bundle/embedded-content review.

## Deutsch

### Verantwortlicher und Kontakt

Ragutis Remote for SoundTouch Reborn wird als eigenständiges Community-Projekt veröffentlicht. Ein optionaler Kontakt per E-Mail kann **innerhalb der App im Info-Dialog** vom Nutzer selbst gestartet werden. Alternativ steht der [öffentliche GitHub-Issue-Bereich](https://github.com/K-Cosmo/ragutis-remote-for-str/issues) zur Verfügung. Für eine künftige Google-Play-Veröffentlichung sind die dort angegebenen Entwickler-/Kontaktinformationen maßgeblich. Wir veröffentlichen hier keine vollständige Feedback-Mailadresse als Klartext.

### Gespeicherte Informationen und Zweck

Die App sucht STR-Lautsprecher im lokalen WLAN und zeigt deren vorhandene Fernbedienungsoberfläche. Lokal in Androids App-Speicher können der zuletzt verbundene Lautsprecher, IP-/Hostname und Port, Geräte- und Modellbezeichnungen, ausgewählte oder selbst eingegebene Raumnamen, Kennungen und Zustände noch nicht auf STR umgestellter Bose-Geräte sowie Anzeigeeinstellungen gespeichert sein. Diese Daten dienen Geräteauswahl, Wiederverbindung und Anzeige und werden **nicht durch einen Ragutis-eigenen Server synchronisiert oder für Analytics verwendet**. Es gibt keinen Ragutis-Benutzeraccount und keine eingebauten Werbe- oder Tracking-SDKs.

**Android-Systembackup:** Die veröffentlichte App hat derzeit `android:allowBackup="true"`. Abhängig von Android-Version und Backup-/Geräteübertragungseinstellungen können geeignete lokal gespeicherte App-Daten durch **Androids Systembackup oder Geräteübertragung** gesichert und wiederhergestellt werden. Daher versprechen wir nicht, dass diese Daten das Telefon unter allen Umständen niemals verlassen. Die genau betroffenen Dateien und Google-Play-Datensicherheitsangaben sind vor einer Play-Veröffentlichung gesondert zu prüfen.

### Netzwerkzugriffe und Geräte

Die STR-Weboberfläche wird vom ausgewählten Lautsprecher bzw. STR-Server im lokalen Netz geladen, gewöhnlich über **unverschlüsseltes HTTP**. Das angezeigte, von STR bereitgestellte WebView kann eigene Medien- oder andere Netzwerkzugriffe ausführen. Inhalte und deren weitere Datenübertragungen liegen nicht vollständig unter Kontrolle des Ragutis-Android-Clients; maßgeblich ist daneben die jeweilige STR-Konfiguration.

Bei der optionalen WLAN-Ersteinrichtung überträgt die App die vom Nutzer eingegebene SSID und das Passwort direkt an die **Bose-Original-Firmware** im temporären Einrichtungsnetz – ebenfalls über **unverschlüsseltes HTTP**. Das Passwort wird nicht dauerhaft in Ragutis-Einstellungen oder Protokollen gespeichert; es wird für den konkreten Einrichtungsvorgang benötigt. Diese Funktion sollte nur in einer vertrauenswürdigen Netzwerkumgebung verwendet werden.

### GitHub-Versionsprüfung

Die App kann beim Start und nach Nutzerwahl über „Auf Updates prüfen“ die öffentliche GitHub-Releases-API per **HTTPS** kontaktieren. GitHub erhält dabei die üblichen Verbindungsdaten, etwa IP-Adresse und HTTP-User-Agent. Ragutis überträgt hierfür weder WLAN-Passwort noch Lautsprecher-ID oder Raumnamen. Ein Zeitpunkt der letzten erfolgreichen automatischen Abfrage kann lokal gespeichert werden. Es findet **kein automatischer Download und keine automatische Installation** einer APK statt.

### Freiwillige Rückmeldungen

Nur auf ausdrücklichen Klick öffnet die App eine **nicht abgesendete E-Mail-Vorlage** im installierten E-Mail-Programm, gegebenenfalls mit Modellbezeichnung, manuell gewähltem Testergebnis und App-/Android-Version. Der Nutzer kann Inhalt, Empfänger und Versand vor dem Absenden prüfen. Falls eine Nachricht versendet wird, verarbeiten E-Mail-Anbieter und der Empfänger die üblichen E-Mail-Daten; Ragutis sendet keine Nachricht im Hintergrund.

### Aufbewahrung und Löschung

Lautsprecher-/Raumdaten verbleiben in der Regel bis zum Entfernen innerhalb der App oder zum Löschen der App-Daten über Android-Einstellungen. Eine Deinstallation entfernt normalerweise lokale App-Daten; **gegebenenfalls vorhandene Betriebssystem-Backups** können davon unabhängig bestehen und unterliegen den Backup-/Kontoeinstellungen des Geräts. E-Mails, die der Nutzer selbst versendet, sind beim verwendeten E-Mail-Anbieter bzw. Empfänger zu verwalten. Für Rückfragen kann der oben genannte Kontaktweg genutzt werden.

## English

### Operator and contact

Ragutis Remote for SoundTouch Reborn is published as an independent, unofficial community project. Users can open a voluntary support **email draft** from the app's About screen or contact the project using [GitHub Issues](https://github.com/K-Cosmo/ragutis-remote-for-str/issues). Any future Google Play developer/support contact information is displayed in the Play listing. No complete feedback mailbox is printed in this public repository.

### Locally saved information and purpose

The Android app discovers STR speakers on the local Wi-Fi network and displays the existing STR remote. Android app-private storage may contain the last connected speaker, host/IP and port, device and model labels, built-in/custom room labels, temporary original-firmware Bose device setup status/identity and local UI choices. These are used for device selection and reconnection. **Ragutis does not run its own sync server, user account, advertising or analytics backend or SDK.**

**System backup:** The currently published Android app has `android:allowBackup="true"`. Eligible saved app data may be included in **Android system/cloud backups or device transfers**, subject to OS and user settings. We therefore do not promise that locally saved data can never leave a device. Exact data inclusion and any corresponding Play disclosures require a separate review of the delivered app.

### LAN communication, STR WebView and setup

The selected STR speaker/server provides the remote over local **unencrypted HTTP**. The embedded STR website can make its own media or network requests; its behavior may depend on the owner's STR setup and is not fully controlled by the Android wrapper.

The optional Wi-Fi wizard sends the user-supplied network SSID and password directly to **original Bose firmware** over **unencrypted HTTP** on a temporary setup Wi-Fi network. The password is used only for the active setup request and is not intentionally persisted in app preferences or logs. Perform setup only in a trusted environment.

### GitHub update checks

At startup or after a manual check, Ragutis may contact GitHub's public Releases API using **HTTPS**. GitHub may receive ordinary request metadata including the public IP address and HTTP User-Agent, but not local speaker identifiers, rooms or Wi-Fi passwords. The most recent successful automatic check time may be kept in app-private preferences. The app does not silently download or install other APKs.

### Optional feedback email

Only after a user requests feedback does the app open a **draft** in an external email client. It may contain a user-entered speaker model, chosen result, and app/Android version. The user can review/edit/cancel; nothing is sent in the background. If sent, the user's email service and the recipient process ordinary email metadata.

### Retention and deletion

Local speaker/room preferences normally remain until removed within the app or cleared using Android's app-storage settings. Uninstalling usually removes local app data, **but OS-managed backup copies may exist separately** and can be controlled in the device/account backup settings. Messages sent through an external email client are managed by that provider and recipient. Use the contact methods above for privacy questions.

---

**Project source:** https://github.com/K-Cosmo/ragutis-remote-for-str

**Scope:** This describes Ragutis's own Android functionality and identified third-party interactions. Any Google Play Data safety answers must be audited against the actual AAB, STR WebView traffic, current permissions and Android backup policy before submission.
