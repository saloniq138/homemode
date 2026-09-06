# HomeMod

Mod Fabric dla Minecraft 26.2 dodający komendy `/sethome` i `/home`.

## Wymagania

- Java 25 (Minecraft 26.1+ tego wymaga)
- Gradle 9.5.1 (lub wrapper wygenerowany lokalnie poleceniem `gradle wrapper --gradle-version 9.5.1`, jeśli plik `gradle-wrapper.jar` nie jest dołączony w archiwum)

## Budowanie

```
./gradlew build
```

lub, jeśli nie masz wrappera:

```
gradle build
```

Gotowy plik znajdziesz w `build/libs/homemod-1.0.0.jar`.

## Działanie

- `/sethome` – zapisuje aktualną pozycję gracza (BlockPos) do pliku `homes.json` w katalogu roboczym serwera.
- `/home` – teleportuje gracza na zapisaną pozycję, zachowując aktualny yaw/pitch. Jeśli home nie istnieje, zwraca komunikat błędu.

## Uwaga dot. paczki

Ze względu na ograniczenia środowiska generującego to archiwum, plik binarny `gradle/wrapper/gradle-wrapper.jar` nie został dołączony (wymagałby pobrania z sieci). Plik `gradle-wrapper.properties` już wskazuje na Gradle 9.5.1 – wystarczy uruchomić raz `gradle wrapper` z lokalnie zainstalowanym Gradle, aby dogenerować wrapper, albo budować bezpośrednio poleceniem `gradle build`.
