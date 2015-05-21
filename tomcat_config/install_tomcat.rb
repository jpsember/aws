#!/usr/bin/env ruby
require 'js_base'

puts "Using homebrew to install tomcat, and create symbolic link from ~/tomcat"

USER = ENV['USER']
HOME_DIR = File.expand_path('~')
TOMCAT_DIR = File.join(HOME_DIR,"tomcat")

if File.symlink?(TOMCAT_DIR)
  File.unlink(TOMCAT_DIR)
end

scall("brew install tomcat")
scall("sudo ln -s /usr/local/Cellar/tomcat/8.0.22/libexec #{TOMCAT_DIR}")
scall("sudo chown -R #{USER} #{TOMCAT_DIR}")
