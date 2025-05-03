latest_jar=$(ls -t ~/coding/homeroom/src/app/build/libs/*-all.jar | head -n 1)
# copy Linux JFX
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-base-21-linux.jar ~/coding/homeroom/src/pkg/lib/linux/
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-graphics-21-linux.jar ~/coding/homeroom/src/pkg/lib/linux/
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-fxml-21-linux.jar ~/coding/homeroom/src/pkg/lib/linux/
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-controls-21-linux.jar ~/coding/homeroom/src/pkg/lib/linux/
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-media-21-linux.jar ~/coding/homeroom/src/pkg/lib/linux/

# copy mac JFX
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-base-21-mac.jar ~/coding/homeroom/src/pkg/lib/mac/
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-graphics-21-mac.jar ~/coding/homeroom/src/pkg/lib/mac/
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-fxml-21-mac.jar ~/coding/homeroom/src/pkg/lib/mac/
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-controls-21-mac.jar ~/coding/homeroom/src/pkg/lib/mac/
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-media-21-mac.jar ~/coding/homeroom/src/pkg/lib/mac/

# copy mac-aarch64 JFX
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-base-21-mac-aarch64.jar ~/coding/homeroom/src/pkg/lib/mac-aarch/
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-graphics-21-mac-aarch64.jar ~/coding/homeroom/src/pkg/lib/mac-aarch/
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-fxml-21-mac-aarch64.jar ~/coding/homeroom/src/pkg/lib/mac-aarch/
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-controls-21-mac-aarch64.jar ~/coding/homeroom/src/pkg/lib/mac-aarch/
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-media-21-mac-aarch64.jar ~/coding/homeroom/src/pkg/lib/mac-aarch/

# copy win JFX
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-base-21-win.jar ~/coding/homeroom/src/pkg/lib/win/
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-graphics-21-win.jar ~/coding/homeroom/src/pkg/lib/win/
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-fxml-21-win.jar ~/coding/homeroom/src/pkg/lib/win/
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-controls-21-win.jar ~/coding/homeroom/src/pkg/lib/win/
cp ~/coding/homeroom/src/app/build/javafx-libs/javafx-media-21-win.jar ~/coding/homeroom/src/pkg/lib/win/

cp "$latest_jar" ~/coding/homeroom/src/pkg/app-all.jar
chmod +x ~/coding/homeroom/src/pkg/run.sh