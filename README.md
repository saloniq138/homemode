# HomeMod

Server-side Fabric mod for Minecraft 26.2.

## Funkcje

- **Piątek:** `keepInventory` jest automatycznie wyłączane.
- **Sobota:** `keepInventory` jest automatycznie włączane ponownie.
- Mod działa po stronie serwera — gracze nie muszą instalować moda na kliencie.
- Przy zmianie dnia serwer wysyła graczom informację o zmianie.

## Wymagania

- Minecraft 26.2
- Fabric Loader 0.19.3+
- Fabric API
- Java 25
- Gradle 9.5.1

## Budowanie

```bash
gradle build
```

Gotowy JAR znajdziesz w `build/libs/`.

GitHub Actions automatycznie buduje moda po każdym pushu do `main` i udostępnia JAR jako artifact.
