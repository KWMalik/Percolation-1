classpath = "#{ENV['CLASSPATH']}:#{Dir.pwd + File::SEPARATOR}*:"
task :default => ["Percolation.class", "PercolationTest.class"]
task :clean do
  sh "rm *class"
end

file "Percolation.class" => ["Percolation.java"] do
  sh "javac -g Percolation.java"
end

file "PercolationTest.class" => ["PercolationTest.java"] do
  sh "javac -g PercolationTest.java -classpath #{classpath}"
end

file "PercolationStats.class" => ["PercolationStats.java"] do
  sh "javac -g PercolationStats.java -classpath #{classpath}"
end

task :run => ["Percolation.class"] do
  sh "java Percolation"
end

task :test => :default do
  sh "java -classpath #{classpath} org.junit.runner.JUnitCore PercolationTest"
end

task :stats => ["PercolationStats.class", "Percolation.class"] do
  sh "java -classpath #{classpath} PercolationStats 200 100"
  sh "java -classpath #{classpath} PercolationStats 2 100000"
end

task :debug => [:default, "PercolationStats.class"] do
  sh "jdb -launch -classpath #{classpath} PercolationStats 10 2"
end
