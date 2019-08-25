
Gem::Specification.new do |s|
  s.name = 'smalld-gettingstarted-ruby'
  s.authors = ['Princess Lana']
  s.version = '0.0.0'
  s.summary = 'Getting started with SmallD and JRuby'

  s.platform = 'java'

  s.requirements << 'jar com.github.princesslana, smalld, 0.1.0'
  s.requirements << 'jar org.slf4j, slf4j-simple, 1.7.25'

  s.add_development_dependency 'jar-dependencies', '0.4.0'
  s.add_development_dependency 'rake', '12.3.2'
end
