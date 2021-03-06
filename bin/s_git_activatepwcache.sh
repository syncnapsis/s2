#
# Syncnapsis Framework - Copyright (c) 2012-2014 ultimate
# 
# This program is free software; you can redistribute it and/or modify it under the terms of
# the GNU General Public License as published by the Free Software Foundation; either version
# 3 of the License, or any later version.
# 
# This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
# without even the implied warranty of MECHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
# See the GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Plublic License along with this program;
# if not, see <http://www.gnu.org/licenses/>.
#
echo INFO: This will set global git config!
echo INFO: Credentials will be cached on every usage for about 15 minutes.
echo Do you really want to enable your credentials caching globally? Y/N

valid=0

while [ $valid -eq 0 ]
do
	read -n1 CHAR	
	echo
	
	if [ $CHAR = "y" ] || [ $CHAR = "Y" ]
	then
		valid=1
		accept=1
	fi
	if [ $CHAR = "n" ] || [ $CHAR = "N" ]
	then
		valid=1
		accept=0
	fi
	
	if [ $valid -eq 0 ]
	then
		echo invalid selection
	fi
done

if [ $accept -eq 1 ]
then
	echo enabling credential caching
	git config --global credential.helper cache
fi