# Pantheon

Android application for downloading and converting eBooks.

## Prerequisites

- Android SDK 21+
- Gradle 7.0+
- Java 11+

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/kj114022/Pantheon_apk.git
   cd Pantheon_apk
   ```

2. Configure `local.properties`:
   ```bash
   echo "sdk.dir=$ANDROID_HOME" > local.properties
   ```

## Build

```bash
./gradlew build
```

## Run

Install on connected device or emulator:

```bash
./gradlew installDebug
```

### Emulator Commands

List available emulators:

```bash
emulator -list-avds
```

Start an emulator:

```bash
emulator -avd <emulator_name>
```

Build and run on emulator:

```bash
./gradlew installDebug && adb shell am start -n io.github.aloussase.booksdownloader/.ui.MainActivity
```

Clear app data on emulator:

```bash
adb shell pm clear io.github.aloussase.booksdownloader
```

Uninstall from emulator:

```bash
adb uninstall io.github.aloussase.booksdownloader
```

## Test

Run unit tests:
```bash
./gradlew test
```

Run instrumented tests:
```bash
./gradlew connectedAndroidTest
```

## Features

- Search for eBooks
- Download from multiple sources
- Built-in eBook format converter
- Support for EPUB, PDF, MOBI, AZW3

## License

MIT
