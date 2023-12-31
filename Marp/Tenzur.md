---
marp: true
theme: rose-pine-moon
transition: fade
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

![bg 75%](Assets/Frontend.png)

---

# <span style="view-transition-name: Backend">Backend</span>

## <span style="view-transition-name: Listeners">Listeners</span>

- Starten wenn der Server startet
- Bilden Komponenten ab die immer erreichbar sind
- Werden gestoppt wenn der Server stoppt

---

<!-- paginate: hold -->

# <span style="view-transition-name: Backend">Backend</span>

## <span style="view-transition-name: Listeners">Listeners</span>

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

# <span style="view-transition-name: Backend">Backend</span>

## Permission Manager

- Prüft auf jeder Seite ob der User berechtigt ist
- Extra Funktionen zum Prüfen der Seiten und User

---

# <span style="view-transition-name: Backend">Backend</span>

## Database Manager

- Wird initialisiert mit den nötigen Daten
- Erstellt eine SessionFactory und setzt Konfiguration für SQL
- Fügt ggf. Testdaten in die Datenbank ein

---

# <span style="view-transition-name: Backend">Backend</span>

## Login Controller

- API Schnittstelle zur Benutzer Authentifizierung
- GET Requests leiten weiter
- POST Requests führen zur Authentifizierung

---

# <span style="view-transition-name: Backend">Backend</span>

## <span style="view-transition-name: Authorization-Manager">Authorization Manager</span>

```java
public class AuthorizationManager {
    private EllipticCurveJsonWebKey senderJwk = null;
    private EllipticCurveJsonWebKey receiverJwk = null;

    public AuthorizationManager() throws JoseException { this.generateKeyPair(); }
    private void generateKeyPair() throws JoseException

    @NotNull
    public String generateJWT(@NotNull long userID) throws JoseException

    @NotNull
    private JsonWebSignature getJsonWebSignature(@NotNull long userID)

    public String validateToken(@NotNull String token)

    @NotNull
    public String hashPassword(@NotNull String password)

    @NotNull
    public BCrypt.Result verifyPassword(@NotNull String password, @NotNull String hashedPassword)
}
```

---

<!-- paginate: hold -->

# <span style="view-transition-name: Backend">Backend</span>

## <span style="view-transition-name: Authorization-Manager">Authorization Manager</span>

```java
private EllipticCurveJsonWebKey senderJwk = null;
private EllipticCurveJsonWebKey receiverJwk = null;

private void generateKeyPair() throws JoseException
```

Keys werden beim start der Anwendung generiert und im RAM gespeichert.

> Beide Keys werden nur für die Verschlüsselung der LoginSessions verwendet.

---

<!-- paginate: hold -->

# <span style="view-transition-name: Backend">Backend</span>

## <span style="view-transition-name: Authorization-Manager">Authorization Manager</span>

```java
@NotNull
public String hashPassword(@NotNull String password)

@NotNull
public BCrypt.Result verifyPassword(@NotNull String password, @NotNull String hashedPassword)
```

BCrypt wird als sicherer Password-Hashsing-Algorithmus verwendet.

> Der Cost-Factor von 15 wurde gewählt.

---

<!-- paginate: true -->

# Datenbank

Tabelle_Id wurde überall auf nur Id gekürzt.

* User → Benutzer
	* Reduziert auf Benutzername, E-Mail, Rolle, Passwort
* Einnahmeuebersicht → Einnahme
* Kontoverbindung → Konto
* Mieterstammdaten → Mieter
* Rolle
	* Wurde zu einem festdefinierten Enum

---

# Unsere Erkenntnisse

---

<!-- paginate: false -->

# <!--fit--> Danke für Ihre Aufmerksamkeit
