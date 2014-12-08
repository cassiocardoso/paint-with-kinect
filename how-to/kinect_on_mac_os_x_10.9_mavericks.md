# Kinect on Mac OS X 10.9 Mavericks

## 1) Desinstalar todos os programas

[How to uninstall Microsoft Kinect drivers](http://blog.nelga.com/how-to-uninstall-microsoft-kinect-drivers-xquartz-cmake-macports-openni-nite-sensorkinect-on-your-mac/)

- Uninstall XQuartz
- Uninstall CMake
- Uninstall MacPorts
- Uninstall OpenNI
- Uninstall NiTE
- Uninstall SensorKinect
- Upgrade XCode

## 2) Instalar adicionais


### 2.1) Instalar e fazer o upgrade do XCode

- Baixar pela Mac App Store
- Versão atual 6.0.1
- XCode > Open Developer Tools > More Developer Tools...
- Download: Command Line Tools (OS X Mavericks) for XCode 6.

### 2.2) Instalar o XQuartz

- [Xquartz site](https://xquartz.macosforge.org/landing/)
- Versão atual: 2.4.7

### 2.3) Instalar o MacPorts 

- [Macports](http://www.macports.org/install.php)
- Versão atual: 2.3.1

### 2.4) Instalar CMake

- [CMake download](http://www.cmake.org/download/)
- Versão atual: 3.0.2

## 3) Instalar dependencias

### 3.1) Libtool

`sudo port install libtool`

### 3.2) Libusb

## 4) Instalar o SDK do OpenNI

- [Download OpenNI v1.5.7.10 SDK for Mac OS X](https://mega.co.nz/#!Hc5kwAiZ!uJiLY4180QGXjKp7sze8S3eDVU71NHiMrXRq0TA7QpU)
- `cd OpenNI directory`
- `./install.sh`

## 5) Instalar o SensorKinect

### Preparando a instalação

- `sudo ln -s /usr/local/bin/niReg /usr/bin/niReg`

### Instalando o SensorKinect

- Entre no [GitHub do projeto](https://github.com/avin2/SensorKinect).
- Clique em `Download ZIP`.
- Copie o arquivo para sua pasta do Kinect e de um duplo clique para extrair.
- Acesse a pasta `Bin` e encontre o arquivo `SensorKinect093-Bin-MacOSX-v5.1.2.1.tar.bz2`, dê um duplo clique para extrair.
- No terminal digite `cd ` e arraste a pasta que acabou de ser extraída.
- Então rode o comando de instalação `sudo ./install.sh`.

## 6) Instalar o NiTE

- [Download do NITE v1.5.2.21 for MAC OS X](https://mega.co.nz/#!nZYwgJiQ!m091FXc4U6GwjRfpHK-puPvBjkHdWc6KmQH-_RzXfOw)
- Copie o arquivo baixado para sua pasta do Kinect.
- De um duplo clique para extrair o conteúdo do arquivo.
- No terminal digite `cd ` e arraste a pasta extraída.
- Após isso rode o comando de instalação `sudo ./install.sh`.

## 7) Transferir e testar arquivos de exemplo

- Copie os arquivos XML de `NiTE/Data` para a pasta `SensorKinect/Data` (arraste e segure a tecla `option` para copiar os arquivos).
- Localize o diretório: `NiTE/Samples/Bin/x64-Release`.
- Abra o terminal, digite `cd ` e arraste o diretório `x64-Release` para lá e rode o comando.
- 
Erro de 10-20s e congela.

Próximos passos:

## 8) Instalar libfreenect.

Seguir o passo a passo descrito em: https://github.com/OpenKinect/libfreenect/tree/osx-absolute#fetch-build

- Instalar o HomeBrew

## Links

http://blog.nelga.com/setup-microsoft-kinect-on-mac-os-x-10-9-mavericks/

http://blog.nelga.com/setup-microsoft-kinect-on-mac-os-x-10-8-mountain-lion/

http://openkinect.org/wiki/Getting_Started#Manual_Build_under_OSX

https://github.com/OpenKinect/libfreenect/pull/325

https://github.com/OpenKinect/libfreenect/issues/316

https://github.com/ofTheo/Kinect1473

https://groups.google.com/forum/#!topic/simple-openni-discuss/JdVyOh8k7-Y

http://cviz.wordpress.com/2013/05/18/openni-sensorkinect/

http://blog.nelga.com/how-to-use-quartz-composer-synapse-and-an-xbox-kinect-to-create-special-effects-on-your-mac/

