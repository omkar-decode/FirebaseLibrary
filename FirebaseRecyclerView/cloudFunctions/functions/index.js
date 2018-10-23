
const functions = require('firebase-functions');
const admin = require('firebase-admin');

admin.initializeApp(functions.config().firebase);

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });

exports.sendLibraryNotification = functions.database.ref('/Library001/{pushId}').onWrite((change, context) => {

	const newBook = change.after.val();

	const payload = {
		
		title: 'New book!',
		name: `${newBook.Name}`,
		author : `${newBook.Author}`
		
	};

	return admin.messaging().sendToTopic('libraryBooks', {data: payload}).then((response) => {

		console.log('Successfully sent notification', response);
		return 0;
	}).catch((error) => {
		console.log('Error sending notification', error);
		throw error;
	});

});
