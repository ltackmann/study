class ProcessBuilder {
   ProcessBuilder() {
     Process process = new Process.start('ls', ['/']);
     StringInputStream stringStream = new StringInputStream(process.stdout);
     stringStream.dataHandler = () {
       String content = stringStream.read();
       print("process: $content");
     };
     process.close();
   }
}
