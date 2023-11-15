---
marp: true
theme: rose-pine-moon
transition: fade
paginate: true
---

<!-- paginate: skip -->

# <!--fit-->Tenzur

Tobias Ochott, Lars Belitz, Burak Kablan, Robin Walter, Laura Boniecki, Marwin Parchainski

---

<!-- paginate: true -->

# Inhaltsverzeichnis

1. <span style="view-transition-name: Unsere-Ziele">Unsere Ziele</span>
2. Software Vorstellung
3. Frontend
4. Backend
5. Datenbank
6. Reflexion

---

# <span style="view-transition-name: Unsere-Ziele">Unsere Ziele</span>

- Verwaltung von Immobilienobjekten
- Verwaltung von Mietern (Objekt Mieter)
- Verwaltung und Nebenkosten, Zahlungen und Außenständen
- Dokumentenmanagement
- Benutzerverwaltung
- Dashboard Übersicht
- Sortier-, Filter- und Suchfunktionen

---

# Software Vorstellung

---

# Frontend

---

# Backend
- Tobias
	- Listeners
		- starten wenn der Server startet
		- Bilden Komponenten ab die immer erreichbar sind
		- Werden gestoppt wenn der Server stoppt
	- Login Controller
		- GET leitet auf login page weiter
		- POST gibt Anmeldedaten an den AuthManager zum authentizieren und gibt bei Erfolg ein Token wieder
	- Database & Permission Manager
		- 
- Lars

---
# Backend
## Listeners
- Starten wenn der Server startet
- Bilden Komponenten ab die immer erreichbar sind
- Werden gestoppt wenn der Server stoppt
---
# Backend
## Listeners
```java
public void contextInitialized(ServletContextEvent event) {
	ServletContext ctx = event.getServletContext();
	AuthorizationManager manager = null;

	try {
		manager = new AuthorizationManager();
	} catch (JoseException e) {
		throw new RuntimeException(e);
	}
	ctx.setAttribute("Auth", manager);
	System.out.println("Authorization Manager intiliazed.");
}
```
---
# Backend
## Permission und Database Manager

---
# Backend
## Login Controller
- API Schnittstelle zur Benutzer Authentifizierung
- GET Requests leiten weiter
- POST Requests führen zur Authentifizierung
---

# Datenbank

---

# Unsere Erkenntnisse

---

<!-- paginate: false -->

# <!--fit--> Danke für Ihre Aufmerksamkeit